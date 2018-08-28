package com.nowcoder.service;

import com.nowcoder.util.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;
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


    //user是否喜欢，喜欢返回1，不喜欢返回-1，否则返回0
    public int getLikeStatus(int userId,int entityId, int entityType) {
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        if (jedisAdapter.sismem(likeKey, String.valueOf(userId))) {
            return 1;
        }
        String dislikeKey = RedisKeyUtil.getDislikeKey(entityId, entityType);
        if (jedisAdapter.sismem(dislikeKey, String.valueOf(userId))) {
            return -1;
        }
        return 0;
    }

    //点赞
    public long like(int userId, int entityId, int entityType) {
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        jedisAdapter.sadd(likeKey, String.valueOf(userId));
        String dislikeKey = RedisKeyUtil.getDislikeKey(entityId, entityType);
        jedisAdapter.srem(dislikeKey, String.valueOf(userId));
        return jedisAdapter.scard(likeKey); //返回当前点赞人数
    }

    //踩
    public long dislike(int userId, int entityId, int entityType) {
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        jedisAdapter.srem(likeKey, String.valueOf(userId));
        String dislikeKey = RedisKeyUtil.getDislikeKey(entityId, entityType);
        jedisAdapter.sadd(dislikeKey, String.valueOf(userId));
        return jedisAdapter.scard(dislikeKey); //返回当前踩人数
    }

}
