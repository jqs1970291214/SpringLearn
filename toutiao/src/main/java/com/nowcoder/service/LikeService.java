package com.nowcoder.service;

import com.nowcoder.util.JedisAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 点赞服务
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/28 16:25
 */
@Service
public class LikeService {
    @Autowired
    private JedisAdapter jedisAdapter;



}
