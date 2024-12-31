/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
/**
 *
 * @author oupma
 */
@ApplicationScoped
public class AuthService {
    private static final String BASE_URL = "http://localhost:12521/Practica1-Backend/rest/api/v1/auth";

    public boolean validateCredentials(String username, String password) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(BASE_URL + "/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(new LoginRequest(username, password)));

        return response.getStatus() == 200;
    }

    public void logout() {
        // Implementar si cal fer alguna acció al backend
    }

    // Classe interna per al login request
    private static class LoginRequest {
        private String username;
        private String password;

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        // Getters i setters...
    }
}
