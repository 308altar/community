package com.csust.community.dto;

import com.csust.community.model.User;
import lombok.Data;

/**
 * @Author XieHaiBin
 * @Date 2020/6/16 21:25
 * @Version 1.0
 */
@Data
public class QuestionDTO { //一个bean，关联了用户和该用户发布的问题
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private User user;
}
