/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.Article;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
/**
 *
 * @author oupma
 */
@ApplicationScoped
public class ArticleService {
    private static final String BASE_URL = "http://localhost:12521/Practica1-Backend/rest/api/v1/article";

    public List<Article> getArticles(String author, String topic) {
        Client client = ClientBuilder.newClient();
        String url = BASE_URL;
        if (author != null) {
            url += "?author=" + author;
        }
        if (topic != null) {
            url += "&topic=" + topic;
        }

        return client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Article>>() {});
    }

    public Article getArticleById(Long id) {
        Client client = ClientBuilder.newClient();
        return client.target(BASE_URL + "/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get(Article.class);
    }
}
