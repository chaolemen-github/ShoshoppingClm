package com.chaolemen.shoppingclm.category.bean;

import com.google.gson.JsonElement;

import java.io.Serializable;

public class CategoryRespoens  {

    /**
     * status : 0
     * message : 分类列表获取成功
     * data : null
     */

    private int status;
    private String message;
    private JsonElement data;

    @Override
    public String toString() {
        return "CategoryRespoens{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }
}
