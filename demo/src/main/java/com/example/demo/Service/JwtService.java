package com.example.demo.Service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {

private final SecretKey key;

public JwtService(@Value("${jwt.secret}") String secret) {
    this.key = Keys.hmacShaKeyFor(
        secret.getBytes()
    );
}


public String generateToken(String email) {

    return Jwts.builder()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .signWith(key)
            .compact();
}
public String extractEmail(String token) {
	return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
}
public boolean isTokenValid(String token , UserDetails userDetails) {
	String tokenEmail=extractEmail(token);
	return tokenEmail.equals(userDetails.getUsername()) && !isTokenExpired(token);
	
}
public boolean isTokenExpired(String token) {

    return extractExpiration(token).before(new Date());
}
public Date extractExpiration(String token) {

    return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getExpiration();
}





}
