package com.csust.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author XieHaiBin
 * @Date 2020/6/15 19:19
 * @Version 1.0
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish(){

        return "publish";
    }

}
