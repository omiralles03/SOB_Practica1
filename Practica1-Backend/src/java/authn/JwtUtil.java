/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authn;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.ws.rs.core.Response;
import java.util.Date;
/**
 *
 * @author oupman
 */
public class JwtUtil {
    // Token parameters
    private static final String SECRET_KEY = "75928276deb2a0a7e3465a05c8706fa8fe6238f7faddbef9045ba0257671f936";
    private static final String ISSUER = "sob_practica1";
    private static final Date expirationToken = new Date(System.currentTimeMillis() + (30L * 86400000)); // 30 day expiration
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); 
    
    // Generate User JWT Token
    public static String generateToken(String username) {
        String generatedJWT = null;
        try {
            generatedJWT= JWT.create()
                    .withSubject(username)
                    .withIssuer(ISSUER)
                    .withExpiresAt(expirationToken)
                    .sign(algorithm);
        } catch (JWTCreationException e ) {
            Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error generating token: " + e.getMessage())
                    .build();
        }
        return generatedJWT;
    }
    
    // Validate JWT Token and return the subject if valid
    public static String validateToken(String token) throws JWTVerificationException{
        DecodedJWT jwt = null;
        try {
            jwt = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Invalid token: " + e.getMessage());
        }
        return jwt.getSubject();
    }
    
    // Token expiration date Getter
    public static Date getExpirationDate(String token) {
        DecodedJWT jwt = JWT.decode(token);
    return jwt.getExpiresAt();
    }
}
