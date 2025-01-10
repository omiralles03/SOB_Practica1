/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
}

