package com.csust.community.service;

import com.csust.community.dto.PageinationDTO;
import com.csust.community.dto.QuestionDTO;
import com.csust.community.exception.CustomizeErrorCode;
import com.csust.community.exception.CustomizeException;
import com.csust.community.mapper.QuestionExtMapper;
import com.csust.community.mapper.QuestionMapper;
import com.csust.community.mapper.UserMapper;
import com.csust.community.model.Question;
import com.csust.community.model.QuestionExample;
import com.csust.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author XieHaiBin
 * @Date 2020/6/16 21:39
 * @Version 1.0
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    /**
     * PageinationDTO 包含QuestionDTO属性，QuestionDTO带有user属性，user里有用户头像地址avatarUrl，用于获取用户头像地址
     *
     * @param page 页码
     * @param size 一个页面的问题数
     * @return 返回当前页码数应显示的问题列表和分页的状态设置
     */
    public PageinationDTO list(Integer page, Integer size) {

        Integer totalPage;
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample()); //数据库中问题总数
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) { //判断page是否合法
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        Integer offset = size * (page - 1);
        //返回数据库中的limit offset,size规格的问题
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");//问题按创建时间倒序排序
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PageinationDTO pageinationDTO = new PageinationDTO();  //存放当前页码的问题列表和分页的状态
        for (Question question : questions) {  //循环找出该问题列表所对应的user
            User user = userMapper.selectByPrimaryKey(question.getCreator());//通过问题存放的creator关联user的id查询
            QuestionDTO questionDTO = new QuestionDTO();  //存放具有user属性的Question
            BeanUtils.copyProperties(question, questionDTO);//快速拷贝到数据传送类DTO中
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        pageinationDTO.setQuestions(questionDTOList);
        pageinationDTO.setPageination(totalPage, page);

        return pageinationDTO;
    }

    /**
     * 返回包含当前用户所发布的问题列表的PageinationDTO
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public PageinationDTO list(Long userId, Integer page, Integer size) {
        Integer totalPage;
        //Integer totalCount = questionMapper.countByUserId(userId);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);//数据库中该用户的问题总数
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) { //判断page是否合法
            page = 1;
        }
        if (totalPage == 0) {
            totalPage = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        Integer offset = size * (page - 1);
        //List<Question> questions = questionMapper.listByUserId(userId, offset, size);//返回数据库中该用户的所有问题
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper
                .selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PageinationDTO pageinationDTO = new PageinationDTO();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());//通过问题存放的creator关联user的id查询
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//快速拷贝到数据传送类DTO中
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        pageinationDTO.setQuestions(questionDTOList);
        pageinationDTO.setPageination(totalPage, page);

        return pageinationDTO;
    }

    public QuestionDTO getById(Long id) {
        //Question question = questionMapper.getById(id);
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //插入新question入库
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        } else {
            question.setGmtModified(System.currentTimeMillis());
            //questionMapper.upDate(question);
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            int updateed = questionMapper.updateByExampleSelective(updateQuestion, example);//更新数据库中表的变化数值,并返回是否更新成功 1 失败 0
            if (updateed != 1) { //没有更新成功
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
  /*      Question question=questionMapper.selectByPrimaryKey(id); //先拿到当前数据库中该问题的相关数据 view_count
        //更新数据库中该问题的view_count
        Question updateQuestion = new Question();
        updateQuestion.setViewCount(question.getViewCount()+1); //访问一下就+1
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andIdEqualTo(id);
        questionMapper.updateByExampleSelective(updateQuestion, example);*/
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        String regexpTag = Arrays
                .stream(tags)
                .filter(StringUtils::isNotBlank)
                .map(t -> t.replace("+", "").replace("*", "").replace("?", ""))
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);

        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}
