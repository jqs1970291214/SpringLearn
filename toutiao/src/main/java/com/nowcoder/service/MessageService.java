package com.nowcoder.service;

import com.nowcoder.dao.MessageDao;
import com.nowcoder.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return messageDao.addMessage(message);
    }
}
