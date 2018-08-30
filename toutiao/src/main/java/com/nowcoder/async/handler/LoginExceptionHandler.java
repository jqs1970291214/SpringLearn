package com.nowcoder.async.handler;

import com.nowcoder.async.EventHandler;
import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventType;

import java.util.Arrays;
import java.util.List;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/30 12:02
 */
public class LoginExceptionHandler implements EventHandler {
    @Override
    public void doHandle(EventModel model) {
        //判断是否异常登录
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LOGIN);
    }

}
