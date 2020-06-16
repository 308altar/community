package com.csust.community.model;

import lombok.Data;

/**
 * @Author XieHaiBin
 * @Date 2020/6/15 9:44
 * @Version 1.0
 */
@Data
public class User {  //数据库表USER
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
