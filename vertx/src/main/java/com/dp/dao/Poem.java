package com.dp.dao;

import io.vertx.core.json.JsonObject;

import java.io.Serializable;

/**
 * @author pengbo
 * @date 2022/7/22 9:32
 * @name:
 */
public class Poem implements Serializable {

    private Integer id;

    public Poem(JsonObject result) {
    }

    public Poem(String id, String header, String content, String useDay, String imgUrl, String attach) {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JsonObject toJson() {
        return new JsonObject();
    }
}
