package com.csust.community.dto;

import com.csust.community.model.User;
import lombok.Data;

/**
 * @Author XieHaiBin
 * @Date 2020/6/16 21:25
 * @Version 1.0
 */
@Data
public class QuestionDTO {
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
    private User user;
}
