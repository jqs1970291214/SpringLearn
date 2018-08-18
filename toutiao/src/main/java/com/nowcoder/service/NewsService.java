package com.nowcoder.service;

import com.nowcoder.dao.NewsDao;
import com.nowcoder.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:Junqson
 * create:2018/3/31 15:24
 * des:
 */
@Service
public class NewsService {
    @Autowired
    private NewsDao newsDao;

    public List<News> getLatestNews(int userId, int offset, int limit) {
        return newsDao.selectByUserIdAndOffset(userId, offset, limit);
    }
}
