package com.nowcoder.service;

import com.nowcoder.dao.TicketDao;
import com.nowcoder.dao.UserDao;
import com.nowcoder.exception.MyException;
import com.nowcoder.model.Ticket;
import com.nowcoder.model.User;
import com.nowcoder.util.MD5Util;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * author:Junqson
 * create:2018/3/30 21:10
 * des:
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private TicketDao ticketDao;

    public User getUser(int id){
        return userDao.selectById(id);
    }

    public void addUser(User user) {
        userDao.addUer(user);
    }

    public String register(String username, String password) {
        //简单的判断规则
        if (StringUtils.isBlank(username)) {
            return "用户名不能为空";
        }

        if (StringUtils.isBlank(password)) {
            return "密码不能为空";
        }

        char first = username.charAt(0);
        if (!Character.isAlphabetic(first)) {
            return "用户名不合法，只允许字母开头";
        }

        if (StringUtils.contains(username, " ")) {
            return "用户名不允许空格";
        }

        if (!StringUtils.containsOnly(username,
                "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890")) {
            return "用户名只允许字母和数字";
        }

        if (!StringUtils.containsOnly(password, "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890")) {
            return "密码只允许字母和数字";
        }

        User user = userDao.selectByName(username);
        if (user != null) {
            return "用户名已被注册";
        }

        String salt = UUID.randomUUID().toString().substring(0, 5);
        user = new User();
        user.setName(username);
        user.setSalt(salt);

        //注意这里使用了未存储到数据库的公盐
        user.setPassword(MD5Util.encryptPassword(password, salt));
        Random random = new Random();
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));

        userDao.addUer(user);
        return null;
    }

    public String login(String username, String password) {

        //简单的判断规则
        if (StringUtils.isBlank(username)) {
            return "用户名不能为空";
        }

        if (StringUtils.isBlank(password)) {
            return "密码不能为空";
        }

        User user = userDao.selectByName(username);
        if (user == null) {
            return "用户名不存在";
        }

        String salt = user.getSalt();
        String pass = MD5Util.encryptPassword(password, salt);

        //生成并存储ticket
        //可以将生成保存ticket单独提出来，放在这里，注册完成直接登录，优化用户体验
        if (StringUtils.equals(pass, user.getPassword())) {
            String ticket = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
            Ticket loginTicket = new Ticket();
            loginTicket.setTicket(ticket);
            loginTicket.setStatus(0);
            Date time = new Date();
            time.setTime(time.getTime() + 1000 * 60 * 120);//两小时过期
            loginTicket.setExpired(time);
            loginTicket.setUserId(user.getId());
            ticketDao.addTicket(loginTicket);
            return "t" + ticket;
        }

        return "用户名或密码错误";

    }

    public void logout(String ticket) {
        ticketDao.updateStatus(ticket, 1);
    }
}
