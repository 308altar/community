package com.csust.community.service;

import com.csust.community.dto.QuestionDTO;
import com.csust.community.mapper.QuestionMapper;
import com.csust.community.mapper.UserMapper;
import com.csust.community.model.Question;
import com.csust.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 返回一个带有user属性的question，用于获取用户头像地址
     * user里有用户头像地址avatarUrl
     * @return
     */
    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();//返回数据库中的所有问题
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());//通过问题存放的creator关联user的id查询
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//快速拷贝到数据传送类DTO中
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
