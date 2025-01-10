/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 *
 * @author oupma
 */
public class Article {
    private String authorUsername;
    private String authorProfileURL;
    private Long id;
    private String imageURL;
    private boolean isPrivate;
    private Date publishedAt;
    private String summary;
    private String title;
    private Integer views;
    private String body;
    private List<String> topics;

    public Article() {}
    
    public Article(String authorUsername, String authorProfileURL, Long id, String imageURL, boolean isPrivate, Date publishedAt, String summary, String title, Integer views, String body, List<String> topics) {
        this.authorUsername = authorUsername;
        this.authorProfileURL = authorProfileURL;
        this.id = id;
        this.imageURL = imageURL;
        this.isPrivate = isPrivate;
        this.publishedAt = publishedAt;
        this.summary = summary;
        this.title = title;
        this.views = views;
        this.body = body;
        this.topics = topics;
    }

    public String getAuthorProfileURL() {
        return authorProfileURL;
    }

    public void setAuthorProfileURL(String authorProfileURL) {
        this.authorProfileURL = authorProfileURL;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean getIsPrivate() {
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
    

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", body='" + body + '\'' +
                ", topics='" + topics + '\'' +
                ", publishedAt=" + publishedAt +
                ", authorUsername='" + authorUsername + '\'' +
                ", authorProfileURL='" + authorProfileURL + '\'' +
                ", views=" + views +
                ", isPrivate=" + isPrivate +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}

