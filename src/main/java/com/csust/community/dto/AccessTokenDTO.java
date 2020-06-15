package com.csust.community.dto;

import lombok.Data;

/**
 * @Author XieHaiBin
 * @Date 2020/6/15 7:53
 * @Version 1.0
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
