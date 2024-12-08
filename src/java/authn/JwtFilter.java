/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authn;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.Priority;
import jakarta.persistence.*;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import java.security.Principal;
import model.auth.CustomPrincipal;

/**
 *
 * @author oupman
 */

@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter {
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    // Extracts Authorization value from the HTTP request header
    // and checks if exists and includes the Bearer prefix
    // Abort HTTP request if not
    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authorizationHeader = requestContext.getHeaderString("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }
        
        // Validate token by extracting Bearer prefix and white spaces
        String token = authorizationHeader.substring("Bearer".length()).trim();
        try {
            String username = JwtUtil.validateToken(token);
             // Get users ID from serching its username in the DB
            Long userId = em.createQuery("SELECT u.id FROM User u WHERE u.username = :username", Long.class)
                    .setParameter("username", username)
                    .getSingleResult();

            // Create the Security context 
            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return new CustomPrincipal(username, userId);
                }

                @Override
                public boolean isUserInRole(String role) {
                    return false;
                }

                @Override
                public boolean isSecure() {
                    return requestContext.getUriInfo().getRequestUri().getScheme().equals("https");
                }

                @Override
                public String getAuthenticationScheme() {
                    return "Bearer";
                }
            });
        } catch (JWTVerificationException e) {
            // Return HTTP 401 error if invalid or null token
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid or expired JWT token. Please authenticate again.")
                        .build()
            );
        } catch (NoResultException e) {
            // Handle case where the username in the token does not exist in the DB
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("The user associated with this token does not exist.")
                        .build()
            );
        } catch (Exception e) {
            // Catch any other errors like DB issues or unexpected exceptions
            requestContext.abortWith(
                Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("An error occurred while processing the authentication token.")
                        .build()
            );
        }
    }
}
