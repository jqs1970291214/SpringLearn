package com.nowcoder.model;

/**
 * author:Junqson
 * create:2018/3/25 16:29
 * des: 用户实体类
 */
public class User {

    private int id;
    private String name;
    private String password;
    private String salt;
    private String headUrl;

    public User() {
        super();
    }
    public User(String name) {
        this.name = name;
    }
    public User(int id, String name, String password, String salt, String headUrl) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.headUrl = headUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}