/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.UserDTO;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oupma
 */
@Controller
@Path("Users")
public class UsersController {
    @Inject Logger log; 
    @Inject Models models;
    @Inject UserService userService;
    
    @GET
    public String showUsersPage() {
        List<UserDTO> users = userService.getAllUsers();
        System.out.println("USERSDTO "+users);
        models.put("users", users);
        return "pages/users.jsp";
    }
}
