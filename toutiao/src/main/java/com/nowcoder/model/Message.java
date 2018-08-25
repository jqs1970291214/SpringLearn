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
    private int hasRead;
    private String conversationId;
}
