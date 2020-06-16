package com.csust.community.mapper;

import com.csust.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author XieHaiBin
 * @Date 2020/6/15 9:40
 * @Version 1.0
 */
@Mapper
@Repository
public interface QuestionMapper {  //Mybatis Mapper适配器，执行SQL数据库查询
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void insert(Question question);

    @Select("select * from question")
    List<Question> list();
}
