/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authn;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author oupman
 */

@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Extracts Authorization value from the HTTP request header
        // and checks if exists and includes the Bearer prefix
        // Abort HTTP request if not
        String authorizationHeader = requestContext.getHeaderString("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }
        
        // Validate token by extracting Bearer prefix and white spaces
        String token = authorizationHeader.substring("Bearer".length()).trim();
        try {
            String username = JwtUtil.validateToken(token);
            requestContext.setProperty("username", username); // Save username to context request if token is valid
        } catch (JWTVerificationException | IllegalArgumentException e) {
            // Return HTTP 401 error if invalid or null token
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
