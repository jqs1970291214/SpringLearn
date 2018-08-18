package com.nowcoder.controller;

import com.nowcoder.aspect.LogAspect;
import com.nowcoder.model.User;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.ToutiaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * author:Junqson
 * create:2018/3/25 15:20
 * des:
 */
//@Controller
public class IndexController {
    @Autowired
    private ToutiaoService toutiaoService;

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);


    @RequestMapping(path = {"/", "/index"},method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String index(HttpSession session) {
        logger.info("Visit index");
        return "hello world! <br/>" + session.getAttribute("msg") + "<br/>" +
                toutiaoService.say() + "<br/>";
    }

    @RequestMapping(path = {"/profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("groupId") String groupId,
                          @PathVariable("userId") int userId,
                          @RequestParam(value = "type", defaultValue = "1") int type,
                          @RequestParam(value = "key", defaultValue = "nowcoder") String key) {
        return String.format("group: %s user: %d  type: %d  key: %s ", groupId, userId, type, key);
    }

    //此处的model也进行注入
    @RequestMapping("/news")
    public String news(Model model) {

        List<String> color = Arrays.asList(new String[]{"Red", "Blue", "Pink", "Green", "Black"});
        Map<String, String> powMap = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            powMap.put(String.valueOf(i), String.valueOf(i * i));
        }
        model.addAttribute("value1", "I'm news one!");
        model.addAttribute("colors", color);
        model.addAttribute("keys", powMap);
        model.addAttribute("user", new User("田亚昊"));

        return "news";
    }

    //下面参数会由spring进行自动注入
    @RequestMapping("/request")
    @ResponseBody
    public String request(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse,
                          HttpSession httpSession) {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        sb.append("下面是headers：<br/>");
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            sb.append(name + ":" + httpServletRequest.getHeader(name) + "<br/>");
        }

        sb.append("下面是cookies： <br/>");
        for (Cookie cookie : httpServletRequest.getCookies()) {
            sb.append("Cookie:" + cookie.getName() + "=" + cookie.getValue() + "<br/>");
        }
        sb.append("getMethod:" + httpServletRequest.getMethod() + "<br/>");
        sb.append("getPathInfo:" + httpServletRequest.getPathInfo() + "<br/>");
        sb.append("getQueryString:" + httpServletRequest.getQueryString() + "<br/>");
        sb.append("getRequestURI:" + httpServletRequest.getRequestURI() + "<br/>");

        httpServletResponse.addHeader("customer-header","my header");
        httpServletResponse.addCookie(new Cookie("sessionidfromserver",httpSession.getId()));

        return sb.toString();
    }

    @RequestMapping("/response")
    @ResponseBody
    public String response(@CookieValue(value = "nowcoderid", defaultValue = "a") String nowcoder,
                           @RequestParam(value = "key", defaultValue = "key") String key,
                           @RequestParam(value = "value", defaultValue = "value") String value,
                           HttpServletResponse httpResponse
    ) {
        httpResponse.addCookie(new Cookie(key, value));
        httpResponse.addHeader(key, value);
        return "NowcoderId from cookie: " + nowcoder;


    }

    @RequestMapping("/redirect/{code}")
    public RedirectView redirect(@PathVariable("code") int code) {
        RedirectView redirectView = new RedirectView("/", true);
        //如果状态码为301 进行永久跳转 否则临时跳转
        if (code == 301) {
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        return redirectView;
    }

    @RequestMapping("/redirect2")
    public String redirect2(HttpSession session) {
        session.setAttribute("msg", "Jump from redirect.");
        return "redirect:/"; //永远是302跳转
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin(@RequestParam(value = "key", required = false) String key) {
        if ("admin".equals(key)) {
            return "hello," + key;
        }
        throw new IllegalArgumentException("Key 错误");
    }




    //自定义异常处理
    @ExceptionHandler
    @ResponseBody
    public String error(Exception e) {
        return e.getMessage();
    }




}
