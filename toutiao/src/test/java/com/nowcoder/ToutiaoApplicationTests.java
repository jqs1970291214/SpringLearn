package com.nowcoder;

import com.nowcoder.dao.CommentDao;
import com.nowcoder.dao.MessageDao;
import com.nowcoder.dao.UserDao;
import com.nowcoder.model.Comment;
import com.nowcoder.model.Message;
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

	@Autowired
	MessageDao messageDao;



	@Test
	@Transactional //会自动回滚
	public void contextLoads() {
		List<Message> messages = messageDao.getConversationDetail("1_12", 0, 10);
		System.out.println(messages.toString());

	}

}
