package com.nowcoder.util;

/**
 * 生成rediskey
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/28 20:29
 */
public class RedisKeyUtil {
    //分隔符
    private static String SPLIT = ":";

    //赞和踩
    private static String BIZ_LIKE = "LIKE";
    private static String BIZ_UNLIKE = "DISLIKE";

    public static String getLikeKey(int entityId, int entityType) {
        return BIZ_LIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }

    public static String getDislikeKey(int entityId, int entityType) {
        return BIZ_UNLIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }



}
