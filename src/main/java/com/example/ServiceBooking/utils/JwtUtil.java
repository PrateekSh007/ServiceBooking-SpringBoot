package com.example.ServiceBooking.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    public static final String SECRET_KEY = String.valueOf(Keys.hmacShaKeyFor("mysecretkeymysecretkeymysecretkey".getBytes(StandardCharsets.UTF_8)));

    private String createToken(Map<String , Object> claims, String userName){

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 6))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact() ;
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes) ;
    }

    public String generateToken(String userName) {
        Map<String,Object> claims = new HashMap<>() ;
        return createToken(claims,userName) ;
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsRecover){
        final Claims claims = getClaims(token) ;
        return claimsRecover.apply(claims) ;
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration) ;
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject) ;
    }

    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date()) ;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token) ;
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)) ;
    }
}
