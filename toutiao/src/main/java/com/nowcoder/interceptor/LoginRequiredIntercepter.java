package com.nowcoder.interceptor;

import com.nowcoder.annotation.LoginRequired;
import com.nowcoder.exception.MyException;
import com.nowcoder.model.UserHolder;
import com.nowcoder.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 拦截要求登陆的方法
 * 实现登录拦截的两种方案：
 * - addPatterns中配置ant匹配
 * - 自定义注解
 *
 * @author Junqson
 * @date 2018/8/16 21:09
 */
@Component
public class LoginRequiredIntercepter extends HandlerInterceptorAdapter {

    @Autowired
    private UserHolder holder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Method method = ((HandlerMethod) handler).getMethod();
        if (method.isAnnotationPresent(LoginRequired.class)) {
            if (holder.getUser() != null) {
                return true;
            } else {
                // response.sendRedirect("/index?pop=1");可以选择重定向并传递参数，以在重定向过后的页面显示不同的处理
                throw new MyException(ResultEnum.LOGIN_REQUIRED);
            }
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
