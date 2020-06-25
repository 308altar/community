package com.csust.community.exception;

/**
 * @Author XieHaiBin
 * @Date 2020/6/19 14:33
 * @Version 1.0
 */
public class CustomizeException extends RuntimeException {  //自定义抛异常类
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code=errorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
