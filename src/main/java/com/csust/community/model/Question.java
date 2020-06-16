package com.csust.community.model;

import lombok.Data;

/**
 * @Author XieHaiBin
 * @Date 2020/6/16 9:01
 * @Version 1.0
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Long commentCount;
    private Long viewCount;
    private Long likeCount;
}
