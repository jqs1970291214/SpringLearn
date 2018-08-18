package com.nowcoder.interceptor;

import com.nowcoder.dao.TicketDao;
import com.nowcoder.dao.UserDao;
import com.nowcoder.model.Ticket;
import com.nowcoder.model.User;
import com.nowcoder.model.UserHolder;
import com.nowcoder.util.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

/**
 * 获取当前用户
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/16 17:35
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserDao userDao;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private UserHolder holder;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && "ticket".equals(cookie.getName())) {
                    ticket = DigestUtil.Decrypt(cookie.getValue());
                    break;
                }
            }
        }

        if (ticket != null) {
            Ticket ticket1 = ticketDao.selectByTicket(ticket);
            //判断ticket是否能用
            if (ticket1 == null || ticket1.getExpired().before(new Date()) || ticket1.getStatus() != 0) {
                return true;
            }
            User user = userDao.selectById(ticket1.getUserId());
            //查到用户的具体信息后，可将用户信息保存起来，可以通过request的attribute保存（仅限controller），但是service层等无法读取
            //可以使用Spring的方式，保存到ThreadLocal上下文中
            if (user != null) {
                holder.setUser(user);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        User user = null;
        if (modelAndView != null && (user = holder.getUser()) != null) {
            //把user传给视图
            modelAndView.addObject("user", user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //走完流程之后清除掉
        holder.clear();
    }
}
