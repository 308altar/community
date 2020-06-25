package com.csust.community.dto;

import lombok.Data;

/**
 * @Author XieHaiBin
 * @Date 2020/6/25 13:34
 * @Version 1.0
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private String sort;
    private Long time;
    private String tag;
    private Integer page;
    private Integer size;
}
