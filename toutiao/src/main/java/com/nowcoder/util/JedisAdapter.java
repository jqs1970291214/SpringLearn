package com.nowcoder.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Jedis适配器，封装jedis
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/28 15:37
 */
@Service
@Slf4j
public class JedisAdapter implements InitializingBean {
    private JedisPool jedisPool = null;

    @Value("${spring.redis.password}")
    private String redisPass;

    @Override
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool("localhost", 6379);
    }


    public Jedis getResources() {
        Jedis jedis = jedisPool.getResource();
        jedis.auth(redisPass);
        return jedis;
    }

    //向集合中添加
    public long sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = this.getResources();
            return jedis.sadd(key, value);
        } catch (Exception e) {
            log.error("Jedis错误:[{}]", e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    //从集合中删除
    public long srem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = this.getResources();
            return jedis.srem(key, value);
        } catch (Exception e) {
            log.error("Jedis错误:[{}]", e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    //是否是集合成员
    public boolean sismem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = this.getResources();
            return jedis.sismember(key, value);
        } catch (Exception e) {
            log.error("Jedis错误:[{}]", e.getMessage());
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    //集合中的数量
    public long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = this.getResources();
            return jedis.scard(key);
        } catch (Exception e) {
            log.error("Jedis错误:[{}]", e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    //入队
    public long lpush(String key,String value) {
        Jedis jedis = null;
        try {
            jedis = this.getResources();
            return jedis.lpush(key,value);
        } catch (Exception e) {
            log.error("Jedis错误:[{}]", e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    //出队
    public String rpop(String key) {
        Jedis jedis = null;
        try {
            jedis = this.getResources();
            return jedis.rpop(key);
        } catch (Exception e) {
            log.error("Jedis错误:[{}]", e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    //阻塞出队
    public List<String> brpop(int timeout, String key) {
        Jedis jedis = null;
        try {
            jedis = this.getResources();
            return jedis.brpop(timeout, key);
        } catch (Exception e) {
            log.error("Jedis错误:[{}]", e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
