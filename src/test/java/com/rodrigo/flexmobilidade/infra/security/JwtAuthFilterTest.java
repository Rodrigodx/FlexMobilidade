package com.rodrigo.flexmobilidade.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JwtAuthFilterTest {

    public static final String TOKEN = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    private String token;
    private String username = "test";

    private String authHeader;

    @InjectMocks
    private JwtAuthFilter jwtAuthFilter;
    @Mock
    private JwtService service;
    @Mock
    private UserInfoUserDetailsService userDetailsService;
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final FilterChain filterChain = mock(FilterChain.class);
    private final UserDetails userDetails = mock((UserDetails.class));

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void doFilterInternal() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn("Bearer 5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437");
        when(service.extractUsername(TOKEN)).thenReturn(username);

        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        when(service.validateToken(TOKEN, userDetails)).thenReturn(true);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        verify(userDetailsService, times(1)).loadUserByUsername(username);
        verify(service, times(1)).validateToken(TOKEN, userDetails);
        verify(filterChain, times(1)).doFilter(request, response);
    }

}