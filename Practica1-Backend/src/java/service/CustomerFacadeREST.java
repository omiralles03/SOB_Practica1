/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import java.util.List;
import model.entities.Link;
import model.entities.User;
import model.entities.UserDTO;

/**
 *
 * @author oupman, gerard
 */
@Stateless
@Path("customer")
public class CustomerFacadeREST {
    
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        // Get a list of all users
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        
        // Build a list with the users info and return it
        List<UserDTO> userDTOs = users.stream().map(user-> {
            Long lastArticleId = user.getLastArticleId(em);
            Link link = lastArticleId != null ? new Link("/article/" + lastArticleId) : null;

            return new UserDTO(
                user.getUsername(),
                link,
                user.getImageURL()
            );
        }).toList();
        
        return Response.ok(userDTOs).build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") Long id) {
        // Find user by ID
        User user = em.find(User.class, id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found")
                    .build();
        }

        // Get users last article and return its info 
        Long lastArticleId = user.getLastArticleId(em);
        Link link = lastArticleId != null ? new Link("/article/" + lastArticleId) : null;
        
        UserDTO userDTO = new UserDTO(
                user.getUsername(),
                link,
                user.getImageURL()
        );

        return Response.ok(userDTO).build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response updateUser(@PathParam("id") Long id, User updatedUser, @Context SecurityContext securityContext) {

        // Find user by ID
        User user = em.find(User.class, id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Customer not found")
                    .build();
        }

        // Update client data if provided
        if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty())
            user.setUsername(updatedUser.getUsername());
        
        // Update client data if provided
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty())
            user.setPassword(updatedUser.getPassword());
        if (updatedUser.getImageURL() != null && !updatedUser.getImageURL().isEmpty())
            user.setImageURL(updatedUser.getImageURL());

        em.merge(user);

        return Response.ok("User updated successfully").build();
    }
    
    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(User newUser) {
        // Comprova si ja existeix un usuari amb aquest username
        Long existingUserCount = em.createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                .setParameter("username", newUser.getUsername())
                .getSingleResult();

        if (existingUserCount > 0) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Username already exists.")
                    .build();
        }

        // Comprova si la contrasenya i el username són vàlids
        if (newUser.getUsername() == null || newUser.getUsername().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Username cannot be empty.")
                    .build();
        }

        if (newUser.getPassword() == null || newUser.getPassword().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Password cannot be empty.")
                    .build();
        }
        
        // Imatge per defecte
        newUser.setImageURL("https://randomuser.me/api/portraits/lego/1.jpg");
        System.out.println("THE BACKEND IN FOKIN USER CREATED LMFAO");
 
        // Guarda el nou usuari
        em.persist(newUser);

        return Response.status(Response.Status.CREATED)
                .entity("User registered successfully.")
                .build();
    }
}
