package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.dto.user.UsersRequestDto;
import com.rodrigo.flexmobilidade.exceptions.UserPresentException;
import com.rodrigo.flexmobilidade.model.user.UserRole;
import com.rodrigo.flexmobilidade.model.user.Users;
import com.rodrigo.flexmobilidade.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceTest {

    public static final String NAME = "Test";
    public static final String EMAIL = "tests@email.com";
    public static final String PASSWORD = "12345";

    public static final String ID = "1";
    public static final UserRole USER_ROLE = UserRole.USER;
    public static UsersRequestDto USERS_REQUEST_DTO = new UsersRequestDto(NAME, EMAIL, PASSWORD);
    public static Users USERS = new Users(ID, NAME, EMAIL, PASSWORD, USER_ROLE);
    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository repository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenNewUserRegistrationThenReturnSuccess() throws UserPresentException {
        when(repository.findByEmail(any())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(USERS);

        UsersRequestDto response = service.newUserRegistration(USERS_REQUEST_DTO);
        response.setPassword(PASSWORD);

        assertNotNull(response);
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenNewUserRegistrationThenReturnUserPresentException() throws UserPresentException {
        when(repository.findByEmail(any())).thenReturn(Optional.ofNullable(USERS));

        try{
            service.newUserRegistration(USERS_REQUEST_DTO);
            fail();
        }catch (Exception e){
            assertEquals(UserPresentException.class, e.getClass());
        }
    }

    @Test
    void whenAuthenticateThenReturnSuccess() {
        when(authenticationManager.authenticate(any())).thenReturn(new UsernamePasswordAuthenticationToken(EMAIL, PASSWORD));

        service.authenticate(EMAIL, PASSWORD);
    }

    @Test
    void whenAuthenticateThenReturnUsernameNotFoundException() {
        when(authenticationManager.authenticate(any()));
        try {
            service.authenticate(EMAIL, PASSWORD);
            fail();
        }catch (Exception e){
            assertEquals(UsernameNotFoundException.class, e.getClass());
        }
    }

    private void startUser(){
        USERS_REQUEST_DTO = new UsersRequestDto(NAME, EMAIL, PASSWORD);
        USERS = new Users(ID, NAME, EMAIL, PASSWORD, USER_ROLE);
    }
}