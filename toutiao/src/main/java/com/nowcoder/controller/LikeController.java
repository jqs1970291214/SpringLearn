package com.nowcoder.controller;

import com.nowcoder.annotation.LoginRequired;
import com.nowcoder.model.UserHolder;
import com.nowcoder.service.LikeService;
import com.nowcoder.service.NewsService;
import com.nowcoder.util.ApiResult;
import com.nowcoder.util.EntityType;
import com.nowcoder.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/28 20:53
 */
@Controller
public class LikeController {

    @Autowired
    private UserHolder holder;

    @Autowired
    private LikeService likeService;

    @Autowired
    private NewsService newsService;


    //*** 都返回点赞的数量（只关注点赞的数量，不记录点踩的数量） ***

    @LoginRequired
    @RequestMapping("/like")
    @ResponseBody
    public ApiResult like(@RequestParam("newsId") int newsId) {
        int userId = holder.getUser().getId();
        long likeCount = likeService.like(userId, newsId, EntityType.ENTITY_NEWS);
        newsService.updateLikeCount(newsId, (int) likeCount);
        ApiResult apiResult = ResultUtil.success();
        apiResult.put("likeCount", likeCount);
        return apiResult;
    }

    @LoginRequired
    @RequestMapping("/dislike")
    @ResponseBody
    public ApiResult dislike(@RequestParam("newsId") int newsId) {
        int userId = holder.getUser().getId();
        long likeCount = likeService.dislike(userId, newsId, EntityType.ENTITY_NEWS);
        newsService.updateLikeCount(newsId, (int) likeCount);
        ApiResult apiResult = ResultUtil.success();
        apiResult.put("likeCount", likeCount);
        return apiResult;
    }






}
