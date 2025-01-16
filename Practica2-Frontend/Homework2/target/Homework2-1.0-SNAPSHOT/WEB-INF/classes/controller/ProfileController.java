/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.Article;
import deim.urv.cat.homework2.service.ArticleService;
import deim.urv.cat.homework2.service.AuthService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oupma
 */
@Controller
@Path("Profile")
public class ProfileController {
    
    @Inject Logger log; // Logger per depurar    
    @Inject ArticleService articleService;
    @Inject AuthService authService;
    @Inject Models models; // Per injectar dades al JSP

    @GET
    public String showProfilePage(@Context HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        Long articleId = articleService.getLastArticleId(userId);
        if(articleId != null) {
            Article article = articleService.getArticleById(articleId);
            models.put("article", article);
        }
        return "pages/profile.jsp";
    }
}