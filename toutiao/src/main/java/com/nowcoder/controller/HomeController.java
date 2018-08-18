package com.nowcoder.controller;

import com.nowcoder.model.News;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.NewsService;
import com.nowcoder.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.privilegedactions.NewSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * author:Junqson
 * create:2018/3/31 15:22
 * des: 模板页面
 */
@Controller
@Slf4j
public class HomeController {


    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    private List<ViewObject> getNews(int userId, int offset, int limit) {
        List<News> newsList = newsService.getLatestNews(userId, offset, limit);
        List<ViewObject> vos = new ArrayList<>();
        for (News news : newsList) {
            ViewObject viewObject = new ViewObject();
            viewObject.set("user", userService.getUser(news.getUserId()));
            viewObject.set("news", news);
            vos.add(viewObject);

        }

        return vos;
    }


    @RequestMapping(path = {"/", "/index"},method = {RequestMethod.GET,RequestMethod.POST})
    public String index(Model model) {
        model.addAttribute("vos", getNews(0,0,10));
        return "home";
    };

    @RequestMapping(path = {"user/{userId}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String userIndex(Model model, @PathVariable("userId") int userId) {
        model.addAttribute("vos", getNews(userId, 0, 10));
        return "home";
    };




}
