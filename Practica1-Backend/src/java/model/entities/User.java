/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 *
 * @authors gerard, oupman
 */

@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findUser", 
            query = "SELECT u FROM User u WHERE u.username = :username"),
    
    @NamedQuery(name = "User.getLastArticleId", 
            query = "SELECT a.id FROM Article a WHERE a.authorId = :userId ORDER BY a.publishedAt DESC")
})
public class User implements Serializable{
    private static final long serialVersionUID = 1L;  
    
    @Id
    @SequenceGenerator(name="User_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_Gen")
    @Column(name = "id", nullable = false)
    private Long id;
        
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    
    @Column(nullable = false, length = 30)
    private String password;
    
    @Transient
    private Link links;
    
    public Long getLastArticleId(EntityManager em) {
        try {
            return em.createNamedQuery("User.getLastArticleId", Long.class)
                    .setParameter("userId", this.id)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Link getLinks() {
        return links;
    }

    public void setLinks(Link links) {
        this.links = links;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}


