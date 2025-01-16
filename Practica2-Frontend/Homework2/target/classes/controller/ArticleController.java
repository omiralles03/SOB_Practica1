/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.Article;
import deim.urv.cat.homework2.service.ArticleService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oupma
 */
@Controller
@Path("Article")
public class ArticleController {

    @Inject Logger log;
    @Inject ArticleService articleService;
    @Inject Models models;

    @GET
    @Path("{id}")
    public String showArticlePage(@PathParam("id") Long id) {

        Article article = articleService.getArticleById(id);
        if (article == null) {
            return "pages/login.jsp";
        }
        models.put("article", article);
        return "pages/article.jsp";
    }
    
    @GET
    @UriRef("search")
    public String searchArticles(@QueryParam("query") String query, @QueryParam("topic") List<String> topics, @QueryParam("author") String author) {
        List<Article> articles = articleService.searchArticles(query, topics, author);
        models.put("articles", articles);
        return "pages/home.jsp";
    }
}