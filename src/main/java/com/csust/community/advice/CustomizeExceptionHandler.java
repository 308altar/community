package com.csust.community.advice;

import com.csust.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author XieHaiBin
 * @Date 2020/6/19 14:05
 * @Version 1.0
 */
@ControllerAdvice
public class CustomizeExceptionHandler {  //拦截所有异常
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model){
        if(e instanceof CustomizeException){
            model.addAttribute("message",e.getMessage()); //知道异常类型Question Id 异常返回
        }else{
            model.addAttribute("message","服务器错误,请稍后再试!"); //不知道异常
        }
        return new ModelAndView("error");
    }
}
