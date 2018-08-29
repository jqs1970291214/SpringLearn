package com.nowcoder.async;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowcoder.util.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 事件产生器
 * descripttions
 * @author Junqson
 * @date 2018/8/29 14:46
 */
@Service
@Slf4j
public class EventProducer {
    @Autowired
    private JedisAdapter jedisAdapter;

    @Autowired
    private ObjectMapper mapper;

    public void fireEvent(EventModel model){
        try {
            String jmodel = mapper.writeValueAsString(model);
            String queueKey = RedisKeyUtil.getEventQueueKey();
            jedisAdapter.lpush(queueKey, jmodel);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException:[{}]", e.getMessage());
        } catch (Exception e) {
            log.error("fireEvent Exception:[{}]", e.getMessage());
        }
    }

}
