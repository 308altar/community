package com.csust.community.controller;

import com.csust.community.dto.PageinationDTO;
import com.csust.community.mapper.UserMapper;
import com.csust.community.model.User;
import com.csust.community.service.QuestionService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author XieHaiBin
 * @Date 2020/6/17 16:37
 * @Version 1.0
 */
@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          @RequestParam(name="page",defaultValue = "1") Integer page,
                          @RequestParam(name="size",defaultValue = "2") Integer size,
                          Model model) {
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies!=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user==null){
            return "redirect:/";
        }
        if ("questions".contains(action)) {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".contains(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        PageinationDTO pageinationDTO=questionService.list(user.getId(),page,size);
        model.addAttribute("pageination",pageinationDTO);
        return "profile";
    }
}
