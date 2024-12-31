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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oupma
 */
@Controller
@Path("Home")
public class HomeController {
    
    @Inject Logger log; // Logger per depurar    
    @Inject ArticleService articleService;
    @Inject Models models; // Per injectar dades al JSP

    @GET
    public String showHomePage() {
        log.log(Level.INFO, "FOAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        List<Article> articles = articleService.getAllArticles();
        models.put("articles", articles);
        return "pages/home.jsp";
    }
}