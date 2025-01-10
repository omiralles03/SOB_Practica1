/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.User;
import deim.urv.cat.homework2.service.ArticleService;
import deim.urv.cat.homework2.service.AuthService;
import deim.urv.cat.homework2.service.UserService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.mvc.security.CsrfProtected;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oupma
 */
@Controller
@Path("Auth")
public class AuthController {
    @Inject Logger log; 
    @Inject AuthService authService;
    @Inject Models models;
    @Inject UserService userService;
    
    @GET
    public String showLoginPage() {
        return "pages/login.jsp";
    }
    
    @POST
    @UriRef("login")
    @CsrfProtected
    public String handleLogin(@FormParam("username") String username, @FormParam("password") String password, @Context HttpServletRequest request) {
        boolean success = authService.login(username, password);
        if (success) {
            request.getSession().setAttribute("authToken", authService.getToken()); // Save token to session
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("userId", authService.getUserId());
            request.getSession().setAttribute("profileImageURL", authService.getUserProfileURL());
            return "redirect:/Home";
        } else {
            request.setAttribute("error", "Invalid username or password");
            return "pages/login.jsp";
        }
    }
}
