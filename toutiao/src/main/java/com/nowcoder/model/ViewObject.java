package com.nowcoder.model;

import java.util.HashMap;
import java.util.Map;

/**
 * author: Junqson
 * created: 2018/3/31 16:19
 * des: 封装Map，简单model
 */
public class ViewObject {
    private Map<String, Object> objs = new HashMap<>();

    public void set(String key, Object object) {
        objs.put(key, object);
    }

    public Object get(String key) {
        return objs.get(key);
    }

    public Map<String, Object> getObjs() {
        return objs;
    }

    public void setObjs(Map<String, Object> objs) {
        this.objs = objs;
    }
}
