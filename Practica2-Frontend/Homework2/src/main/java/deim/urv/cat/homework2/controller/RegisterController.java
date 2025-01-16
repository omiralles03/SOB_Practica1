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
@Path("Register")
public class RegisterController {
    @Inject Logger log;
    @Inject Models models;
    @Inject UserService userService;
    
    @GET
    public String showRegisterPage() {
        return "pages/register.jsp";
    }
    
    @POST
    @UriRef("register")
    @CsrfProtected
    public String handleRegister(@FormParam("username") String username, @FormParam("password") String password, @Context HttpServletRequest request) {
        boolean success = userService.register(username, password);

        if (success) {
            request.setAttribute("message", "User registered successfully.");
            return "pages/login.jsp";
        } else {
            request.setAttribute("error", "Username already exists, try another one");
            return "pages/register.jsp";
        }
    }
}
