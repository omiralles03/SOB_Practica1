/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author oupma
 */
public class Article {
    private String authorUsername;
    private String imageURL;
    private boolean isPrivate;
    private Date publishedAt;
    private String summary;
    private String title;
    private Integer views;

    public Article() {
    }

    public Article(String authorUsername, String imageURL, boolean isPrivate, Date publishedAt, String summary, String title, Integer views) {
        this.authorUsername = authorUsername;
        this.imageURL = imageURL;
        this.isPrivate = isPrivate;
        this.publishedAt = publishedAt;
        this.summary = summary;
        this.title = title;
        this.views = views;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }


    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", publishedAt=" + publishedAt +
                ", authorUsername='" + authorUsername + '\'' +
                ", views=" + views +
                ", isPrivate=" + isPrivate +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}

