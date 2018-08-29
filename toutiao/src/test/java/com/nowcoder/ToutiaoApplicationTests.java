package com.nowcoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventProducer;
import com.nowcoder.async.EventType;
import com.nowcoder.dao.CommentDao;
import com.nowcoder.dao.MessageDao;
import com.nowcoder.dao.UserDao;
import com.nowcoder.model.Comment;
import com.nowcoder.model.Message;
import com.nowcoder.model.User;
import com.nowcoder.util.EntityType;
import com.nowcoder.util.JedisAdapter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

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


    @Autowired
    JedisAdapter jedisAdapter;


    @Autowired
    ObjectMapper mapper;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private EventProducer eventProducer;


    @Test
    @Transactional //会自动回滚
    public void contextLoads() throws JsonProcessingException {
//		List<Message> messages = messageDao.getConversationDetail("1_12", 0, 10);
//		System.out.println(messages.toString());

        //List<Message> messages = messageDao.getConversationList("12", 0, 10);
        //System.out.println(messages.toString());
//		Jedis jedis = jedisAdapter.getResources();
////		System.out.println(jedisAdapter.redisPass);
//		System.out.println(jedis.get("a"));


//		User user = new User();
//		user.setId(1);
//		user.setName("Junqson");
//		user.setPassword("123123");
//		System.out.println(mapper.writeValueAsString(user));

//		System.out.println(ToStringBuilder.reflectionToString(applicationContext));


        eventProducer.fireEvent(new EventModel(EventType.LIKE)
                .setActorId(1).setEntityId(23)
                .setEntityType(EntityType.ENTITY_NEWS)
                .setEntityOwnerId(0));
    }

}
