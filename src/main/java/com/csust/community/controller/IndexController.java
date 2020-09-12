package com.csust.community.controller;

import com.csust.community.cache.HotTagCache;
import com.csust.community.dto.PageinationDTO;
import com.csust.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author XieHaiBin
 * @Date 2020/6/15 7:37
 * @Version 1.0
 */
@Controller
public class IndexController { //首页控制
    @Autowired
    private HotTagCache hotTagCache;

    @Autowired
    private QuestionService questionService;

    /**
     * 显示首页或根据搜索关键字所显示的问题列表
     * @param model
     * @param page
     * @param size
     * @param search
     * @param tag
     * @param sort
     * @return
     */
    @GetMapping("/")
    public String mainPage(Model model,
                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "5") Integer size,
                           @RequestParam(name = "search", required = false) String search,
                           @RequestParam(name = "tag", required = false) String tag) {
        PageinationDTO pageination = questionService.list(search, tag, page, size);//查询带有用户信息的问题列表返回前端展示
        List<String> tags = hotTagCache.getHots();
        model.addAttribute("pageination", pageination);
        model.addAttribute("search", search);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", tags);
        return "index";
    }
}
