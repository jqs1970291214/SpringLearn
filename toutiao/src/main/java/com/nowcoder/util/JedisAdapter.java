package com.nowcoder.util;

import redis.clients.jedis.Jedis;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/27 17:33
 */
public class JedisAdapter {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        jedis.auth("root");
        jedis.set("a", "b");
        System.out.println(jedis.get("a"));
    }
}
