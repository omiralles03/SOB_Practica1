package deim.urv.cat.homework2.model;

import java.util.Map;

public class UserDTO {
    private String imageURL;
    private String username;

    public UserDTO() {}
    
    public UserDTO(String imageURL, String username) {
        this.imageURL = imageURL;
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "imageURL=" + imageURL +
                ", username='" + username + '\'' +
                '}';
    }
}