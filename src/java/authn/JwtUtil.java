/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authn;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;
/**
 *
 * @author oupman
 */
public class JwtUtil {
    private static final String SECRET_KEY = "75928276deb2a0a7e3465a05c8706fa8fe6238f7faddbef9045ba0257671f936";
    private static final String ISSUER = "sob_practica1";
    private static final Date expirationToken = new Date(System.currentTimeMillis() + 2592000);
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); 
    
    // Generate User JWT Token
    public static String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer(ISSUER)
                .withExpiresAt(expirationToken)
                .sign(algorithm);
    }
    
    // Validate JWT Token and return user if valid
    public static String validateToken(String token) throws JWTVerificationException{
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT.getSubject();
    }
    
}
