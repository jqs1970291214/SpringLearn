package com.nowcoder.async.handler;


import com.nowcoder.async.EventHandler;
import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventType;

import java.util.Arrays;
import java.util.List;

/**
 * 点赞处理器
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/29 14:43
 */
public class LikeHandler implements EventHandler {
    @Override
    public void doHandle(EventModel model) {
        System.out.println("LIKED");
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
