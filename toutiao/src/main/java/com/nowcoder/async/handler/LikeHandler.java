package com.nowcoder.async.handler;


import com.nowcoder.async.EventHandler;
import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventType;
import com.nowcoder.model.Message;
import com.nowcoder.model.User;
import com.nowcoder.service.MessageService;
import com.nowcoder.service.UserService;
import com.nowcoder.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 点赞处理器
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/29 14:43
 */
@Component
public class LikeHandler implements EventHandler {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Override
    public void doHandle(EventModel model) {
        User user = userService.getUser(model.getActorId());
        Message message = new Message();
        message.setFromId(AppUtils.ADMIN_ID);
        message.setToId(model.getEntityOwnerId());
        message.setContent("用户:" + user.getName() + " 赞了你的资讯 " + AppUtils.DOMAIN + "news/" + model.getEntityId());
        message.setCreatedDate(new Date());
        messageService.addMessage(message);
        System.out.println("LIKED");
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
