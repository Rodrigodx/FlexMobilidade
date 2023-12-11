package com.rodrigo.flexmobilidade.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String extracUserName(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    boolean istokenExpired(String token);
}

