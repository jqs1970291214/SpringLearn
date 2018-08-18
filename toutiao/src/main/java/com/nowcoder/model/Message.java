package com.nowcoder.model;

import lombok.Data;

import java.util.TreeSet;

/**
 * 站内信
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/11 22:59
 */
@Data
public class Message {
    private int id;
    private int fromId;
    private int toId;
    private String content;



}
