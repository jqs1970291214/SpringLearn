package com.nowcoder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.TreeSet;

/**
 * 站内信
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/11 22:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private int id;
    private int fromId;
    private int toId;
    private String content;
    private Date createdDate;
    //0未读 1已读
    private int hasRead;
    //生成conversationId 小的id放在前面实现对话共享 1_2 2_1 这样是一个会话 1_2
    private String conversationId;
}
