//this class is responsible for generating and validating JWT tokens

package com.example.demo.security;

import io.jsonwebtoken.*; // this 
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component // this annotations marks the class as a Spring bean , i.e it can be injected
           // into other classes
public class JwtTokenProvider { // this class is responsible for generating and validating JWT tokens

    @Value("${jwt.secret}") // this annotation injects the value of jwt.secret from application.properties
    private String jwtSecret;

    @Value("${jwt.expiration}") // this annotation injects the value of jwt.expiration from
                                // application.properties
    private long jwtExpiration;

    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes()); // this creates a secret key from the jwtSecret

        return Jwts.builder() // this creates a JWT builder
                .subject(email) // sets the subject of the token
                .issuedAt(now) // sets the issue time
                .expiration(expiryDate) // sets the expiration time
                .signWith(secretKey) // signs the token with the secret key
                .compact(); // returns the compact JWT string
    }

    // this method returns the email from the token
    public String getEmailFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    // this method checks if the token is valid
    public boolean validateToken(String token) {
        try {
            getAllClaimsFromToken(token); // this method parses the token and returns the claims , if it fails then the
                                          // token is invalid
            return true;
        } catch (JwtException | IllegalArgumentException e) { // if the token is invalid then return false
            return false;
        }

    }

    // this method returns the claims from the token i.e the email and expiration
    // time
    private Claims getAllClaimsFromToken(String token) {
        SecretKey secretkey = Keys.hmacShaKeyFor(jwtSecret.getBytes()); // this creates a secret key from the jwtSecret
        return Jwts.parser()
                .verifyWith(secretkey)
                .build()
                .parseSignedClaims(token)
                .getPayload(); // this parses the token and returns the claims , i.e if the token is invalid
                               // then it will throw an exception else it will return the claims
    }

}
