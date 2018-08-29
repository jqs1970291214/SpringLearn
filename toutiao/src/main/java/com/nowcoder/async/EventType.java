package com.nowcoder.async;

import lombok.val;

/**
 * 枚举异步事件
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/29 12:31
 */
public enum EventType {
    LIKE(0), //点赞
    COMMENT(1), //评论
    LOGIN(2), //登录
    MAIL(3); //发邮件

    private int value;

    private EventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
