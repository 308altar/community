package com.csust.community.dto;

import com.csust.community.model.User;
import lombok.Data;

/**
 * @Author XieHaiBin
 * @Date 2020/6/22 11:19
 * @Version 1.0
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}
