package com.csust.community.controller;

import com.csust.community.dto.AccessTokenDTO;
import com.csust.community.dto.GithubUser;
import com.csust.community.model.User;
import com.csust.community.provider.GithubProvider;
import com.csust.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author XieHaiBin
 * @Date 2020/6/15 8:03
 * @Version 1.0
 */
@Controller
public class AuthorizeController {  //授权用户的登录
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    /**
     * github回调地址，返回用户参数，写入数据库
     * @param code
     * @param state
     * @param response
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null && githubUser.getName() != null) {//授权成功，更新用户数据至数据库
            User user = new User();
            String token = UUID.randomUUID().toString();//token是根据时间段不断改变的
            user.setToken(token);
            user.setBio(githubUser.getBio());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));//不变的
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.createOfUpdate(user);

            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        }
        return "redirect:/";
    }

    /**
     * 退出该用户，删除session,cookie信息
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String logOut(HttpServletRequest request,
                         HttpServletResponse response) {  //退出登录，应该删除session cookie
        request.getSession().removeAttribute("user"); //移除session
        Cookie cookie = new Cookie("token", null);  //移除cookie只要创建一个同名的置空的cookie即可
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
