package deim.urv.cat.homework2.model;

import java.util.Map;

public class User {
    private String imageURL;
    private Map<String, String> links;
    private String username;

    public User() {}
    
    public User(String imageURL, Map<String, String> links, String username) {
        this.imageURL = imageURL;
        this.links = links;
        this.username = username;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
    public String getImageURL() {
        return imageURL;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "imageURL=" + imageURL +
                "links=" + links +
                ", username='" + username + '\'' +
                '}';
    }
}