package com.nowcoder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 评论，可复用
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/24 22:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int id;
    private String content;
    private int userId;
    private int entityId; //评论的实体
    private Date createdDate;
    private int status; //0 valid 1 invalid
}
