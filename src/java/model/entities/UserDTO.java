/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author oupma
 */
@XmlRootElement
public class UserDTO {
    private Long id;
    private String username;
    private Link links;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, Link links) {
        this.id = id;
        this.username = username;
        this.links = links;
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

    public Link getLinks() {
        return links;
    }

    public void setLinks(Link links) {
        this.links = links;
    }
}
