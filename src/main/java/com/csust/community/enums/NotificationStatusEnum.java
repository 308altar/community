package com.csust.community.enums;

/**
 * @Author XieHaiBin
 * @Date 2020/6/24 11:16
 * @Version 1.0
 */
public enum NotificationStatusEnum {
    UNREAD(0), READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
