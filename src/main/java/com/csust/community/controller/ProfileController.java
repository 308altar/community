package com.csust.community.controller;

import com.csust.community.dto.PageinationDTO;
import com.csust.community.model.User;
import com.csust.community.service.QuestionService;
import com.csust.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author XieHaiBin
 * @Date 2020/6/17 16:37
 * @Version 1.0
 */
@Controller
public class ProfileController { //管理我的问题管理页面
    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "2") Integer size,
                          Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".contains(action)) { //当前操作为 我的问题
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            PageinationDTO pageinationDTO = questionService.list(user.getId(), page, size);
            model.addAttribute("pageination", pageinationDTO);
        } else if ("replies".contains(action)) {//当前操作为 最新回复
            Long unreadCount=notificationService.unreadCount(user.getId());
            PageinationDTO pageinationDTO = notificationService.list(user.getId(), page, size);
            model.addAttribute("section", "replies");
            model.addAttribute("pageination", pageinationDTO);
            model.addAttribute("sectionName", "最新回复");
            model.addAttribute("unreadCount", unreadCount);
        }
        return "profile";
    }
}
