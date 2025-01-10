/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.Article;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author oupma
 */
public class ArticleServiceImpl implements ArticleService {
    private final WebTarget webTarget;
    private final Client client;
    private static final String BASE_URI = "http://localhost:12521/Practica1-Backend/rest/api/v1/article";
    @Inject HttpServletRequest request;
    
    public ArticleServiceImpl() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI);
    }
    
    @Override
    public List<Article> getAllArticles() {
        Response response = webTarget
            .request(MediaType.APPLICATION_JSON)
            .get();

        if (response.getStatus() == 200) {
            List<Article> articles = response.readEntity(new GenericType<List<Article>>() {});
            return articles;
        } else {
            System.out.println("Error en obtenir articles. Status: " + response.getStatus());
            return Collections.emptyList();
        }
    }

    @Override
    public Article getArticleById(Long id) {
        String token = (String) request.getSession().getAttribute("authToken"); // Get the token from the session
        Response response = webTarget
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(Article.class);
        }
        return null;
    }
    
    @Override
    public Long getLastArticleById(Long userId) {
        String reqURI = "http://localhost:12521/Practica1-Backend/rest/api/v1/customer";
        String token = (String) request.getSession().getAttribute("authToken");
        Response response = client.target(reqURI)
            .path(String.valueOf(userId))
            .request(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + token)
            .get();
        
        // Parse the link of last article published by authorId
        if (response.getStatus() == 200) {
            String jsonString = response.readEntity(String.class);
            JsonObject jsonObject;
            try (JsonReader jsonReader = Json.createReader(new StringReader(jsonString))) {
                jsonObject = jsonReader.readObject();
            }
            
            String articleLink = jsonObject.getJsonObject("links").getString("article");
            return Long.valueOf(articleLink.split("/")[2]);
        }
        return null;
    }
}