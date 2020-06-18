package com.csust.community.service;

import com.csust.community.mapper.UserMapper;
import com.csust.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author XieHaiBin
 * @Date 2020/6/18 17:28
 * @Version 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOfUpdate(User user) {
        User dbuser=userMapper.findByAccountId(user.getAccountId());
        if (dbuser==null) { //数据库中没有该用户，第一次登录
            user.setGmtCreate(System.currentTimeMillis()); //创建时间
            user.setGmtModified(user.getGmtCreate()); //修改时间
            userMapper.insert(user);
        }else{//更新字段 name token gmt_modified bio avatar_url
            dbuser.setGmtModified(System.currentTimeMillis());
            dbuser.setName(user.getName());
            dbuser.setToken(user.getToken());
            dbuser.setAvatarUrl(user.getAvatarUrl());
            dbuser.setBio(user.getBio());
            userMapper.upDate(dbuser);
        }
    }
}
