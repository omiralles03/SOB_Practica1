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
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
/**
 *
 * @author oupma
 */
@Controller
@Path("articles")
public class ArticleController {
    @Inject Models models;
    @Inject ArticleService articleService;

    @GET
    @Path("{id}")
    public String showArticle(@PathParam("id") Long id) {
        Article article = articleService.getArticleById(id);
        if (article == null) {
            models.put("message", "Article not found.");
            return "/pages/error.jsp";
        }
        models.put("article", article);
        return "/pages/article-detail.jsp";
    }
}
