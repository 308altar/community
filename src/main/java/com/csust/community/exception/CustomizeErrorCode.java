package com.csust.community.exception;

/**
 * @Author XieHaiBin
 * @Date 2020/6/19 14:59
 * @Version 1.0
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"该问题不存在或已删除。"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复。"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试。");
    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
