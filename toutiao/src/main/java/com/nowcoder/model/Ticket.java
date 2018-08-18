package com.nowcoder.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/14 17:27
 */
@Data
public class Ticket {
    private int id;
    private int userId;
    private String ticket;
    private Date expired;
    private int status;  //1的话ticket无效，视为删除


}
