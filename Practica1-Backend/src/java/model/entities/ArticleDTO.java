/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 *
 * @author oupma
 */
@XmlRootElement
public class ArticleDTO {
    private Long id;
    
    private String title;
    
    @XmlElementWrapper(name = "topics")
    @XmlElement(name = "topic")
    private List<String> topics;
    
    private String summary;
    
    private String body;
    
    private Date publishedAt;
    
    private String authorUsername;
    
    private Integer views;
    
    private boolean isPrivate;
    
    private String imageURL;

    public ArticleDTO() {
    }
    
    public ArticleDTO(Long id, String title, List<String> topics, String summary, String body, Date publishedAt, String authorUsername, Integer views, boolean isPrivate, String imageURL) {
        this.id = id;
        this.title = title;
        this.topics = topics;
        this.summary = summary;
        this.body = body;
        this.publishedAt = publishedAt;
        this.authorUsername = authorUsername;
        this.views = views;
        this.isPrivate = isPrivate;
        this.imageURL = imageURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
        
    @Override
    public String toString() {
        return "ArticleDTO{" +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", topics=" + topics +
                ", summary='" + summary + '\'' +
                ", body='" + body + '\'' +
                ", publishedAt=" + publishedAt +
                ", authorUsername='" + authorUsername + '\'' +
                ", views=" + views +
                ", isPrivate=" + isPrivate +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
