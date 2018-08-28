package com.nowcoder;

import org.apache.commons.collections.set.SynchronizedSortedSet;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.SortedSet;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/27 17:33
 */
public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        jedis.auth("root");
        jedis.set("a", "b");
        System.out.println(jedis.get("a"));


    }
}
