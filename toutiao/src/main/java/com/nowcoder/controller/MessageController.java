package com.nowcoder.controller;

import com.nowcoder.annotation.LoginRequired;
import com.nowcoder.model.Message;
import com.nowcoder.model.User;
import com.nowcoder.model.UserHolder;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.MessageService;
import com.nowcoder.service.UserService;
import com.nowcoder.util.ApiResult;
import com.nowcoder.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/25 21:41
 */
@Controller
@Slf4j
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserHolder userHolder;


    @LoginRequired//必须登录才能发消息
    @RequestMapping("/msg/addMessage")
    @ResponseBody
    public ApiResult addMessage(@RequestParam("fromId") int fromId,
                                @RequestParam("toId") int toId,
                                @RequestParam("content") String content) {

        try {
            Message message = new Message();
            //过滤content，过滤掉其中的敏感词
            message.setContent(content);
            message.setCreatedDate(new Date());
            message.setFromId(fromId);
            message.setToId(toId);
            message.setHasRead(0);
            //生成conversationId 小的id放在前面实现对话共享 1_2 2_1 这样是一个会话 1_2
            String conversationId = fromId > toId ? String.format("%d_%d", toId, fromId) : String.format("%d_%d", fromId, toId);
            message.setConversationId(conversationId);
            messageService.addMessage(message);
        } catch (Exception e) {
            log.error("添加消息失败:[{}]", e.getMessage());
            return ResultUtil.error("添加消息失败");
        }
        return ResultUtil.success();
    }

    @LoginRequired
    @RequestMapping("/msg/list")
    public String conversationList(Model model) {
        try {
            int localUserId = userHolder.getUser().getId();
            List<Message> conversationList = messageService.getConversationList(localUserId, 0, 10);
            //System.out.println(conversationList.toString());
            List<ViewObject> viewObjects = new ArrayList<>();
            for (Message message : conversationList) {
                ViewObject viewObject = new ViewObject();
                viewObject.set("conversation", message);
                //获取对方
                int targetId = localUserId == message.getFromId() ? message.getToId() : message.getFromId();
                User user = userService.getUser(targetId);
                viewObject.set("target", user);
                int unread = messageService.getConversationUnreadCount(localUserId, message.getConversationId());
                viewObject.set("unreadCount", unread);
                viewObjects.add(viewObject);

            }
            model.addAttribute("conversations", viewObjects);
        } catch (Exception e) {
            log.error("获取消息列表失败:[{}]", e.getMessage());
        }
        return "letter";
    }

    @LoginRequired
    @RequestMapping("/msg/detail")
    public String conversationDetail(Model model, @RequestParam("conversationId") String conversationId) {
        try {
            List<Message> messageList = messageService.getConversationDeatil(conversationId, 0, 10);
            List<ViewObject> viewObjects = new ArrayList<>();
            for (Message message : messageList) {
                ViewObject viewObject = new ViewObject();
                viewObject.set("message", message);
                //获取发送者的头像
                User user = userService.getUser(message.getFromId());
                if (user == null) {
                    //忽略本条消息
                    continue;
                }
                viewObject.set("headUrl", user.getHeadUrl());
                viewObject.set("userId", user.getId());
                viewObjects.add(viewObject);

            }
            model.addAttribute("messages", viewObjects);
        } catch (Exception e) {
            log.error("获取对话失败:[{}]", e.getMessage());
        }
        return "letterDetail";
    }
}
