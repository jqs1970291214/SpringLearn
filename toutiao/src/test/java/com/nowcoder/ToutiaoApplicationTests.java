package com.nowcoder;

import com.nowcoder.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
@WebAppConfiguration
public class ToutiaoApplicationTests {
	@Autowired
	UserDao userDao;

	@Test
	public void contextLoads() {

		System.out.println(userDao.selectByName("dsa"));
	}

}
