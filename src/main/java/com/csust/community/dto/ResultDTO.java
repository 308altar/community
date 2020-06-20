package com.csust.community.dto;

import com.csust.community.exception.CustomizeErrorCode;
import com.csust.community.exception.CustomizeException;
import lombok.Data;

/**
 * @Author XieHaiBin
 * @Date 2020/6/20 16:42
 * @Version 1.0
 */
@Data
public class ResultDTO { //返回请求结果标识
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }

    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }
}
