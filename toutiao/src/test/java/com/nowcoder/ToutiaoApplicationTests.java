package com.nowcoder;

import com.nowcoder.dao.CommentDao;
import com.nowcoder.dao.UserDao;
import com.nowcoder.model.Comment;
import com.nowcoder.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
@WebAppConfiguration
public class ToutiaoApplicationTests {
	@Autowired
	UserDao userDao;
	@Autowired
	CommentDao commentDao;

	@Test
	@Transactional //会自动回滚
	public void contextLoads() {
		User user = userDao.selectById(1);

		Comment comment = new Comment();
		comment.setContent("我是测试");
		comment.setCreatedDate(new Date());
		comment.setEntityType(0); //news
		comment.setEntityId(1);
		commentDao.addComment(comment);
		int count = commentDao.getCommentCount(1, 0);
		System.out.println(count);

		List<Comment> comment1 = commentDao.selectByEntity(1, 0);
		System.out.println(comment1.toString());

	}

}
