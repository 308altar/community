package com.csust.community.service;

import com.csust.community.mapper.UserMapper;
import com.csust.community.model.User;
import com.csust.community.model.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());//第一个是创建表达式，第二个是拼接查询参数
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) { //数据库中没有该用户，第一次登录
            user.setGmtCreate(System.currentTimeMillis()); //创建时间
            user.setGmtModified(user.getGmtCreate()); //修改时间
            userMapper.insert(user);
        } else {//更新字段 name token gmt_modified bio avatar_url
            User dbuser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setBio(user.getBio());
            UserExample example = new UserExample();
            example.createCriteria().andAccountIdEqualTo(dbuser.getAccountId());
            userMapper.updateByExampleSelective(updateUser, example);
        }
    }
}
