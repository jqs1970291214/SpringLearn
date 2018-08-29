package com.nowcoder.async;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowcoder.util.JedisAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public void afterPropertiesSet() throws Exception {
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
    }


/*    applicationContext 也可以通过实现ApplicationContextAware接口的setApplicationContext来实现
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }*/
}
