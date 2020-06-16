package com.csust.community.dto;

import lombok.Data;

/**
 * @Author XieHaiBin
 * @Date 2020/6/15 8:42
 * @Version 1.0
 */
@Data
public class GithubUser {  //储存所需的github用户信息
    private String name;
    private long id;
    private String bio;
    private String avatar_url;
}
