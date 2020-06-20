package com.csust.community.advice;

import com.alibaba.fastjson.JSON;
import com.csust.community.dto.ResultDTO;
import com.csust.community.exception.CustomizeErrorCode;
import com.csust.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author XieHaiBin
 * @Date 2020/6/19 14:05
 * @Version 1.0
 */
@ControllerAdvice
public class CustomizeExceptionHandler {  //拦截所有异常
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO;
            //返回JSON
            if (e instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR); //不知道异常
            }

            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter printWriter=response.getWriter();
                printWriter.write(JSON.toJSONString(resultDTO));
                printWriter.close();
            } catch (IOException ioException) {
            }
            return null;
        } else {
            //错误页面跳转
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage()); //知道异常类型Question Id 异常返回
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage()); //不知道异常
            }
            return new ModelAndView("error");
        }

    }
}
