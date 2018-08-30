package com.nowcoder.service;

import com.nowcoder.dao.MessageDao;
import com.nowcoder.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/25 21:44
 */
@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public int addMessage(Message message) {
        if (message.getConversationId() == null) {
            message.setConversationId(message.getFromId() > message.getToId()
                    ? String.format("%d_%d", message.getToId(), message.getFromId())
                    : String.format("%d_%d", message.getFromId(), message.getToId()));
        }
        return messageDao.addMessage(message);
    }
    //获取对话详情
    public List<Message> getConversationDeatil(String conversationId,int offset,int limit) {
        return messageDao.getConversationDetail(conversationId, offset, limit);
    }
    //获取对话列表
    public List<Message> getConversationList(int userId, int offset, int limit) {
        return messageDao.getConversationList(userId, offset, limit);
    }

    //获取对话未读消息数量
    public int getConversationUnreadCount(int userId, String conversationId) {
        return messageDao.getConversationUnreadCount(userId, conversationId);
    }

    //发送消息
}
