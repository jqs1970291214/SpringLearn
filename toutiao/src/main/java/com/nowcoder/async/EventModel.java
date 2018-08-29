package com.nowcoder.async;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 事件模型
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/29 12:31
 */
@NoArgsConstructor
@AllArgsConstructor
public class EventModel {
    //事件类型
    private EventType type;
    //触发者
    private int actorId;
    //触发实体
    private int entityType;
    private int entityId;
    //实体拥有者
    private int entityOwnerId;
    //扩展信息
    private Map<String, String> exts = new HashMap<>();

    public String getExt(String key) {
        return this.exts.get(key);
    }

    public EventModel setExt(String key, String value) {
        this.exts.put(key, value);
        return this; //使其支持链式操作
    }


    public EventModel(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public EventModel setType(EventType type) {
        this.type = type;
        return this;
    }

    public int getActorId() {
        return actorId;
    }

    public EventModel setActorId(int actorId) {
        this.actorId = actorId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public EventModel setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public EventModel setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityOwnerId() {
        return entityOwnerId;
    }

    public EventModel setEntityOwnerId(int entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }

    public Map<String, String> getExts() {
        return exts;
    }

    public EventModel setExts(Map<String, String> exts) {
        this.exts = exts;
        return this;
    }
}
