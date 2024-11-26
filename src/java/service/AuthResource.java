/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.JwtUtil;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entities.User;

/**
 *
 * @author oupman
 */
@Stateless
@Path("auth")
public class AuthResource {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        try {
            // Get the user from the DB using NamedQuery from Crendtials.java
            User foundUser = em.createNamedQuery("User.findUser", User.class)
                    .setParameter("username", user.getUsername())
                    .getSingleResult();
            // Check if the password received matches the one saved on the DB
            if (foundUser.getPassword().equals(user.getPassword())) {
                // Generate a JWT token for the user and return it as a HTTP response
                String token = JwtUtil.generateToken(user.getUsername());
                return Response.ok().entity("{\"token\": \"" + token + "\"}").build();
            } else {
                // If password doesnt match return HTTP 401 error
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (NoResultException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}