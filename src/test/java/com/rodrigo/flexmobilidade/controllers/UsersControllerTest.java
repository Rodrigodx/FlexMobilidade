package com.rodrigo.flexmobilidade.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.rodrigo.flexmobilidade.dto.user.JwtAuthRequest;
import com.rodrigo.flexmobilidade.dto.user.UsersRequestDto;
import com.rodrigo.flexmobilidade.exceptions.UserPresentException;
import com.rodrigo.flexmobilidade.infra.security.JwtService;
import com.rodrigo.flexmobilidade.infra.security.UserInfoUserDetailsService;
import com.rodrigo.flexmobilidade.model.user.UserRole;
import com.rodrigo.flexmobilidade.model.user.Users;
import com.rodrigo.flexmobilidade.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

    public static final String NAME = "Test";
    public static final String EMAIL = "tests@email.com";
    public static final String PASSWORD = "12345";

    public static final String ID = "1";
    public static final UserRole USER_ROLE = UserRole.USER;
    public static JwtAuthRequest AUTH_REQUEST = new JwtAuthRequest(EMAIL, PASSWORD);
    public static UsersRequestDto USERS_REQUEST_DTO = new UsersRequestDto(NAME, EMAIL, PASSWORD);
    public String json;
    public String urlRegister = "/user/registration";
    public String urlLogin = "/user/login";
    public MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @InjectMocks
    private UsersController controller;
    @Mock
    private UserService service;
    @Mock
    private JwtService jService;
    @Mock
    private JwtAuthRequest jwtAuthRequest;
    @Mock
    private UserInfoUserDetailsService userInfoUserDetailsService;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
        json = objectMapper.writeValueAsString(USERS_REQUEST_DTO);
        startUser();
    }

    @Test
    void newUserRegistration() throws Exception {
        when(service.newUserRegistration(any())).thenReturn(USERS_REQUEST_DTO);

        mockMvc.perform(post(urlRegister)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        ResponseEntity<?> response = controller.newUserRegistration(USERS_REQUEST_DTO);

        assertNotNull(response);
        verifyNoMoreInteractions(service);
    }

    @Test
    void userLogin() throws Exception {
        doNothing().when(service).authenticate(any(), any());

        mockMvc.perform(post(urlLogin)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<?> response = controller.userLogin(jwtAuthRequest);

        assertNotNull(response);
        verifyNoMoreInteractions(service);
    }

    private void startUser(){
        USERS_REQUEST_DTO = new UsersRequestDto(NAME, EMAIL, PASSWORD);
        AUTH_REQUEST = new JwtAuthRequest(EMAIL, PASSWORD);
    }
}