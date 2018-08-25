package com.nowcoder.controller;

import com.nowcoder.annotation.LoginRequired;
import com.nowcoder.model.Message;
import com.nowcoder.service.MessageService;
import com.nowcoder.util.ApiResult;
import com.nowcoder.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

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

}
