package com.csust.community.dto;

import lombok.Data;

/**
 * @Author XieHaiBin
 * @Date 2020/6/20 11:31
 * @Version 1.0
 */
@Data
public class CommentCreateDTO {  //评论时,前端返回的数据模型
    private Long parentId;
    private String content;
    private Integer type;
}
