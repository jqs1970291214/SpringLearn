package com.nowcoder.controller;

import com.nowcoder.annotation.LoginRequired;
import com.nowcoder.model.User;
import com.nowcoder.model.UserHolder;
import com.nowcoder.service.UserService;
import com.nowcoder.util.ApiResult;
import com.nowcoder.util.DigestUtil;
import com.nowcoder.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.net.httpserver.HttpServerImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录注册相关
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/13 21:31
 */
@Controller
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserHolder holder;

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }



    @RequestMapping(value = "/reg", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ApiResult reg(@RequestParam("username") String username,
                         @RequestParam("password") String password) {


        String msg = userService.register(username, password);

        if (msg != null) {
            ApiResult res = ResultUtil.error();
            res.setMsg(msg);
            return res;
        }
        return ResultUtil.success("注册成功");
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ApiResult login(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam(value = "remember",defaultValue = "0") int rem,
                           HttpServletResponse response) {

        String msg = userService.login(username, password);

        if (msg != null) {
            //添加到cookies
            if (msg.startsWith("t")) {
                String ticket = msg.substring(1);
                //将token绑定到cookie中，不受session的限制，关闭浏览器下次进去还是可以的
                Cookie cookie = new Cookie("ticket", DigestUtil.Encrypt(ticket));
                cookie.setPath("/");
                if (rem == 1) {
                    //单位是秒
                    cookie.setMaxAge(60 * 60); //一小时，如果不设浏览器关闭后就无效了
                }
                response.addCookie(cookie);
                return ResultUtil.success("登录成功");
            }

            ApiResult res = ResultUtil.error();
            res.setMsg(msg);
            return res;
        }

        return ResultUtil.success("登录成功");
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ApiResult logout(@CookieValue(value = "ticket", required = false) String ticket, HttpServletResponse response) {

        if (ticket != null) {
            userService.logout(DigestUtil.Decrypt(ticket));
            //cookie不用清除，旧的ticket已经无效
            Cookie cookie = new Cookie("ticket", "");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResultUtil.success("注销成功");
        }
        return ResultUtil.error("未登录");
        //return "redirect:/"; 视图的话返回首页
    }

    @LoginRequired
    @RequestMapping("/username")
    @ResponseBody
    public String username() {
        User user = holder.getUser();
        String name = null;
        return user == null ? "" : user.getName();
    }

}
