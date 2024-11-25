package authn;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.auth0.jwt.exceptions.JWTVerificationException;

/**
 *
 * @author oupma
 */
public class JwtUtilTest {
    public static void main(String[] args) {
        // Cas 1: Generació i validació correctes
        try {
            String username = "testuser";
            String token = JwtUtil.generateToken(username);
            System.out.println("Generated Token: " + token);

            String validatedUser = JwtUtil.validateToken(token);
            System.out.println("Valid Token for user: " + validatedUser);
        } catch (JWTVerificationException e) {
            System.out.println("Error during valid case: " + e.getMessage());
        }

        // Cas 2: Validació incorrecta amb un token manipulat
        try {
            String invalidToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImlzcyI6InNvYl9wcmFjdGljYTEiLCJleHAiOjE3MDA4NTk2MDB9.WRONGSIGNATURE";
            String validatedUser = JwtUtil.validateToken(invalidToken);
            System.out.println("Invalid token validated (should not happen): " + validatedUser);
        } catch (JWTVerificationException e) {
            System.out.println("Invalid Token: " + e.getMessage());
        }
    }
    
}
