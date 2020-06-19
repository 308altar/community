package com.csust.community.exception;

/**
 * @Author XieHaiBin
 * @Date 2020/6/19 14:59
 * @Version 1.0
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("该问题不存在或已删除。");
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
