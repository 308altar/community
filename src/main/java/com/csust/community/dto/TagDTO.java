package com.csust.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author XieHaiBin
 * @Date 2020/6/23 16:59
 * @Version 1.0
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
