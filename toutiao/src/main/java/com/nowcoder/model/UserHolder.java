package com.nowcoder.model;

import org.springframework.stereotype.Component;

/**
 * 保存用户信息，全局访问
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/16 19:48
 */
@Component
public class UserHolder {
    //线程本地变量
    private static ThreadLocal<User> user = new ThreadLocal<>();

    public User getUser() {
        return user.get();
    }

    public void setUser(User auser) {
        user.set(auser);
    }

    public void clear() {
        user.remove();
    }

}
