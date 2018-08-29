package com.nowcoder.async;

import java.util.List;

/**
 * 事件处理器接口
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/29 14:41
 */
public interface EventHandler {
    //处理事件
    void doHandle(EventModel model);
    //是否支持
    List<EventType> getSupportEventTypes();

}
