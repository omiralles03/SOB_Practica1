/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author oupman
 */
@Entity
@Table(name = "articles")
@XmlRootElement
public class Article implements Serializable{
    @Id
    @SequenceGenerator(name = "Article_Gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Article_Gen")
    private Long id;
    
    @Column(nullable = false, length = 500)
    private String body;
    
    @Column(nullable = false, length = 100)
    private String title;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date publishedAt;
    
    @Column(nullable = false, length = 100)
    private String summary;
    
    @Column(nullable = false)
    private boolean isPrivate;
    
    @Column(nullable = false)
    private int views;
    
    @Column(nullable = true, length = 255)
    private String imageURL;
    
    @ElementCollection
    @CollectionTable(name = "article_topic", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "topic_id")
    private List<Long> topicIds;
    
    @Column(name = "author_id", nullable = false)
    private Long authorId;

    public Article(Long id, String body, String title, Date publishedAt, String summary, boolean isPrivate, int views, Long authorId, String imageURL) {
        this.id = id;
        this.body = body;
        this.title = title;
        this.publishedAt = publishedAt;
        this.summary = summary;
        this.isPrivate = isPrivate;
        this.views = views;
        this.authorId = authorId;
        this.imageURL = imageURL;
    }

    public Article(){
        
    }
    
    public String getImageURL() {
        return imageURL;
    }
    
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<Long> getTopicIds() {
        return topicIds;
    }

    public void setTopicIds(List<Long> topicIds) {
        this.topicIds = topicIds;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", views=" + views +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
