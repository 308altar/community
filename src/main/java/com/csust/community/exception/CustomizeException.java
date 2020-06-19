package com.csust.community.exception;

/**
 * @Author XieHaiBin
 * @Date 2020/6/19 14:33
 * @Version 1.0
 */
public class CustomizeException extends RuntimeException {  //自定义抛异常
    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
