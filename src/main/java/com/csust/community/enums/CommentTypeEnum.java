package com.csust.community.enums;

/**
 * @Author XieHaiBin
 * @Date 2020/6/20 16:52
 * @Version 1.0
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
