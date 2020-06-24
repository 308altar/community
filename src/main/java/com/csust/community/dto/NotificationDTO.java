package com.csust.community.dto;

import lombok.Data;

/**
 * @Author XieHaiBin
 * @Date 2020/6/24 11:56
 * @Version 1.0
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerId;
    private String typeName;
    private Integer type;
}
