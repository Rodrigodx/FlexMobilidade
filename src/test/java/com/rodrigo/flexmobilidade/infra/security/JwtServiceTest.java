package com.rodrigo.flexmobilidade.infra.security;

import com.rodrigo.flexmobilidade.model.user.UserRole;
import com.rodrigo.flexmobilidade.model.user.Users;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JwtServiceTest {
    private static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    private static final String USERNAME = "test@email.com";
    public static final String ID = "1";
    public static final String NAME = "test";
    public static final String PASSWORD = "12345";
    public static final UserRole USER_ROLE = UserRole.USER;

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExtractUsername() {
        String token = jwtService.generateToken(new Users(ID, NAME, USERNAME, PASSWORD, USER_ROLE));
        String extractedUsername = jwtService.extractUsername(token);
        assertEquals(USERNAME, extractedUsername);
    }

    @Test
    void extractClaim() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("key", "value");
        String token = jwtService.createToken(claims, USERNAME);

        Function<Claims, Object> claimsResolver = claimsMap -> claimsMap.get("key");
        Object extractClaim = jwtService.extractClaim(token, claimsResolver);
        assertEquals("value", extractClaim);
    }

    @Test
    void validateToken() {
        when(userDetails.getUsername()).thenReturn(USERNAME);
        String token = jwtService.generateToken(userDetails);
        assertTrue(jwtService.validateToken(token, userDetails));
    }

    @Test
    void generateToken() {
        String token = jwtService.generateToken(new Users(ID, NAME, USERNAME, PASSWORD, USER_ROLE));
        assertNotNull(token);
    }

}