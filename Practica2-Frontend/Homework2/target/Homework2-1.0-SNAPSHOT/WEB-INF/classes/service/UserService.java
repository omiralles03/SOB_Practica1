/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.User;
import deim.urv.cat.homework2.model.UserDTO;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author oupma
 */
public class UserService {
    private final String BASE_URI = "http://localhost:12521/Practica1-Backend/rest/api/v1/customer";
    private final Client client = ClientBuilder.newClient();
    
    public User getUserById(Long id, String token) {
        Response response = client.target(BASE_URI)
            .path(String.valueOf(id))
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token)
            .get();

    if (response.getStatus() == 200) {
        String jsonResponse = response.readEntity(String.class); // Read as String
        System.out.println("Resposta del backend: " + jsonResponse);

        // Deserialize
        return client.target(BASE_URI)
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .get()
                .readEntity(User.class);
    }
        return null;
    }
    
    public List<UserDTO> getAllUsers() {
        Response response = client.target(BASE_URI)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<UserDTO>>() {});
        } else {
            System.out.println("Error en obtenir articles. Status: " + response.getStatus());
            return Collections.emptyList();
        }
    }
    
    public boolean register(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        Response response = client.target(BASE_URI)
                .path("register")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(new RegisterRequest(username, password)));

        if (response.getStatus() == 201) {
            return true;
        } else {
            System.out.println("Register failed. Status: " + response.getStatus());
            return false;
        }

    }
        
    // Request body for register
    public static class RegisterRequest {

        public String username;

        public String password;

        public RegisterRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

    }
}

