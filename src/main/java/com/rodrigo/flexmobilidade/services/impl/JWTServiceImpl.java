package com.rodrigo.flexmobilidade.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTServiceImpl {

    private String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSingKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserName(String token){
        return extractCLaim(token, Claims::getSubject);
    }

    private <T> T extractCLaim(String token, Function<Claims, T> claimsResolvers){
        final Claims claims = extractAllCLaims(token);
        return claimsResolvers.apply(claims);
    }

    private Key getSingKey(){
        byte[] key = Decoders.BASE64.decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.cThIIoDvwdueQB468K5xDc5633seEFoqwxjF_xSJyQQ");
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractAllCLaims(String token) {
        return Jwts.parser().setSigningKey(getSingKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())) && !istokenExpired(token);
    }

    private boolean istokenExpired(String token) {
        return extractCLaim(token, Claims::getExpiration).before(new Date());
    }

}
