/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author oupman
 */
@Entity
@Table(name = "articles")
public class Article implements Serializable{
    @Id
    @SequenceGenerator(name = "Article_Gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Article_Gen")
    private Long id;
    
    @Column(nullable = false, length = 500)
    private String body;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date publishedAt;
    
    @Column(nullable = false, length = 20)
    private String summary;
    
    @Column(nullable = false, length = 100)
    private String title;
    
    @Column(nullable = false)
    private int views;
    
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private User author;
    
    @ManyToMany
    @JoinTable(name = "article_topic", joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private List<Topic> topics;

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

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return "model.entities.Article[ id=" + id + ", title=" + title + " ]";
    }
}
