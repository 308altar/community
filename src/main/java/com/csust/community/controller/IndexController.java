package com.csust.community.controller;

import com.csust.community.dto.PageinationDTO;
import com.csust.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author XieHaiBin
 * @Date 2020/6/15 7:37
 * @Version 1.0
 */
@Controller
public class IndexController { //首页控制


    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String mainPage(Model model,
                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "2") Integer size) {
        PageinationDTO pageination = questionService.list(page, size);//查询带有用户信息的问题列表返回前端展示
        model.addAttribute("pageination", pageination);
        return "index";
    }
}
