package com.nowcoder.async;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowcoder.util.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件消费器
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/29 14:46
 */
@Service
@Slf4j
public class EventConsumer implements InitializingBean {
    @Autowired
    private JedisAdapter jedisAdapter;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ApplicationContext applicationContext;


    private Map<EventType, List<EventHandler>> config = new HashMap<>();

    @Override
    public void afterPropertiesSet(){
        //寻找ioc容器中的EventHandler，构造EventType对应的处理链
        Map<String, EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if (beans != null) {
            for (Map.Entry<String, EventHandler> entry : beans.entrySet()) {
                List<EventType> supportEventTypes = entry.getValue().getSupportEventTypes();
                for (EventType eventType : supportEventTypes) {
                    if (!config.containsKey(eventType)) {
                        config.put(eventType, new ArrayList<>());
                    }
                    config.get(eventType).add(entry.getValue());
                }
            }
        }
        Runnable runnable = () -> {
            String key = RedisKeyUtil.getEventQueueKey();
            while (true) {
                //一直等,阻塞
                List<String> events = jedisAdapter.brpop(0, key);
                for (String elem : events) {
                    if (elem.equals(key)) {
                        continue;
                    }
                    try {
                        EventModel eventModel = mapper.readValue(elem, EventModel.class);

                        if (!config.containsKey(eventModel.getType())) {
                            log.error("不能识别的EventModel:[{}]");
                            continue;
                        }

                        for (EventHandler handler : config.get(eventModel.getType())) {
                            handler.doHandle(eventModel);
                        }
                    } catch (IOException e) {
                        log.error("IOException:[{}]", e.getMessage());
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();


    }


/*    applicationContext 也可以通过实现ApplicationContextAware接口的setApplicationContext来实现
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }*/
}
