package com.nowcoder;

import com.nowcoder.dao.NewsDao;
import com.nowcoder.dao.UserDao;
import com.nowcoder.model.News;
import com.nowcoder.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
@Sql("/init-schema.sql")
public class InitDatabaseTests {

    private Logger logger = Logger.getLogger("initdatabasetest");


	@Autowired
	UserDao userDao;

	@Autowired
	NewsDao newsDao;

	public void created(){
        Random random = new Random();
        for (int i = 0; i < 11; i++) {
            User user = new User();
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("USER%d", i));
            user.setPassword("");
            user.setSalt("");
            userDao.addUer(user);

            user.setPassword("newpassword");
            userDao.updatePassword(user);

            Assert.assertEquals("newpassword", userDao.selectById(1).getPassword());

            News news = new News();
            news.setCommentCount(i);
            Date date = new Date();
            date.setTime(date.getTime() + 1000 * 3600 * 5 * i);
            news.setCreateDate(date);
            news.setImage(String.format("http://images.nowcoder.com/head/%dm.png", random.nextInt(1000)));
            news.setLikeCount(i + 1);
            news.setUserId(i + 1);
            news.setTitle(String.format("TITLE{%d}", i));
            news.setLink(String.format("http://www.nowcoder.com/%d.html", i));

            newsDao.addNews(news);

        }
    }
    public void deleteTest(){
	    userDao.deleteById(1);
        Assert.assertNull(userDao.selectById(1));
    }
    public void getNews() {
        List<News> newsList = newsDao.selectByUserIdAndOffset(1, 0, 10);
        logger.info(newsList.get(0).getTitle());

    }

	@Test
	public void contextLoad() {
	    created();
	    //deleteTest();
        getNews();
	}

}
