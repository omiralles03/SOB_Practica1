/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.service.AuthService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
/**
 *
 * @author oupma
 */
@Controller
@Path("auth")
public class AuthController {
    @Inject Models models;
    @Inject AuthService authService;

    @GET
    @Path("login")
    public String showLoginForm() {
        return "/pages/login.jsp";
    }

    @POST
    @Path("login")
    public String login(@FormParam("username") String username, @FormParam("password") String password) {
        if (authService.validateCredentials(username, password)) {
            models.put("user", username);
            return "redirect:/home";
        } else {
            models.put("message", "Invalid username or password.");
            return "/pages/login.jsp";
        }
    }

    @GET
    @Path("logout")
    public String logout() {
        authService.logout();
        return "redirect:/home";
    }
}