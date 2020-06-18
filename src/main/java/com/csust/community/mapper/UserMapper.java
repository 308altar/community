package com.csust.community.mapper;

import com.csust.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @Author XieHaiBin
 * @Date 2020/6/15 9:39
 * @Version 1.0
 */
@Mapper
@Repository
public interface UserMapper { //Mybatis Mapper适配器，执行SQL数据库查询
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url,bio) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl},#{bio})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id=#{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl},bio=#{bio} where account_id=#{accountId}")
    void upDate(User user);
}
