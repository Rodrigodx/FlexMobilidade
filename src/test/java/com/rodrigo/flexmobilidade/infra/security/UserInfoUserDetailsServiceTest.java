package com.rodrigo.flexmobilidade.infra.security;

import com.rodrigo.flexmobilidade.model.user.UserRole;
import com.rodrigo.flexmobilidade.model.user.Users;
import com.rodrigo.flexmobilidade.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserInfoUserDetailsServiceTest {
    public static final String USERNAME = "test@email.com";
    public static final String ID = "1";
    public static final String NAME = "test";
    public static final String PASSWORD = "13245";
    public static final UserRole USER_ROLE = UserRole.USER;
    public static final Users USERS = new Users(ID, NAME, USERNAME, PASSWORD, USER_ROLE);
    @InjectMocks
    private UserInfoUserDetailsService service;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(USERS));
        UserDetails userDetails = service.loadUserByUsername(USERNAME);
        assertNotNull(userDetails);
        assertEquals(USERNAME, userDetails.getUsername());
    }
}