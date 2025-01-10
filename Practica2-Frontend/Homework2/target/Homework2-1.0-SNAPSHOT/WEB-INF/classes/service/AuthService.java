/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author oupma
 */
public class AuthService {
    private final String BASE_URI = "http://localhost:12521/Practica1-Backend/rest/api/v1/auth";
    private final Client client = ClientBuilder.newClient();
    private String token;
    private Long userId;
    private String userProfileURL;
    
    public boolean login(String username, String password) {
        Response response = client.target(BASE_URI)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(new LoginRequest(username, password)));

        if (response.getStatus() == 200) {
            JsonObject jsonResponse = response.readEntity(JsonObject.class);
            token = jsonResponse.getString("token");
            userId = jsonResponse.getJsonNumber("id").longValue();
            userProfileURL = jsonResponse.getString("userProfileURL");
            return true;
        } else {
            System.out.println("Login failed. Status: " + response.getStatus());
            return false;
        }
    }

    public String getToken() {
        return token;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public String getUserProfileURL() {
        return userProfileURL;
    }
    
    public void logout() {
        token = null; // Simply clear the token for now
    }

    // Request body for login
    public static class LoginRequest {
        public String username;
        public String password;

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }    
}
