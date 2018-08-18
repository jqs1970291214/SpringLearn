package com.nowcoder.model;

import java.util.Date;

/**
 * author:Junqson
 * create:2018/3/28 19:50
 * des: 新闻实体类
 */
public class News {
    private int id;
    private String title;
    private String link;
    private String image;
    private int likeCount;
    private int commentCount;
    private Date createDate;
    private int userId;

    public News() {

    }



    public News(int id, String title, String link, String image, int likeCount, int commentCount, Date createDate, int userId) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.image = image;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.createDate = createDate;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}