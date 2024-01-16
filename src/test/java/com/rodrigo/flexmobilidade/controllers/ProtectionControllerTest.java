package com.rodrigo.flexmobilidade.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigo.flexmobilidade.dto.protections.ProtectionRequestDTO;
import com.rodrigo.flexmobilidade.dto.protections.ProtectionResponseDTO;
import com.rodrigo.flexmobilidade.model.protections.Protection;
import com.rodrigo.flexmobilidade.services.ProtectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProtectionControllerTest {

    public static final int ID = 1;
    public static final String NAME = "TestName";
    public static final String BENEFITS = "TestBenefits";
    public static final double VALUE = 0.0;
    public static final int INDEX = 0;
    public static Protection PROTECTION = new Protection(ID, NAME, BENEFITS, VALUE);
    public static ProtectionRequestDTO REQUEST_DTO = new ProtectionRequestDTO(NAME, BENEFITS, VALUE);
    public static ProtectionResponseDTO RESPONSE_DTO = new ProtectionResponseDTO(ID, NAME, BENEFITS, VALUE);
    public String json;
    public String url;
    public String urlId;
    public MockMvc mockMvc;
    private final ObjectMapper
            objectMapper = new ObjectMapper();

    @InjectMocks
    private ProtectionController controller;
    @Mock
    private ProtectionService service;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
        json = objectMapper.writeValueAsString(REQUEST_DTO);
        url = "/api/protection";
        urlId = "/api/protection/{id}";
        startProtection();
    }

    @Test
    void whenSaveThenReturnSuccess() throws Exception {
        when(service.save(any())).thenReturn(RESPONSE_DTO);

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        ResponseEntity<ProtectionResponseDTO> response = controller.save(REQUEST_DTO);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(BENEFITS, response.getBody().getBenefits());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(VALUE, response.getBody().getValue());
        verifyNoMoreInteractions(service);
    }

    @Test
    void whenFindAllThenReturnSuccess() throws Exception {
        when(service.findAll()).thenReturn(List.of(RESPONSE_DTO));

        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<List<ProtectionResponseDTO>> response = controller.findAll();

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ID, Objects.requireNonNull(response.getBody()).get(INDEX).getId());
        assertEquals(BENEFITS, response.getBody().get(INDEX).getBenefits());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(VALUE, response.getBody().get(INDEX).getValue());
        verifyNoMoreInteractions(service);
    }

    @Test
    void whenFindByIdThenReturnSuccess() throws Exception {
        when(service.findById(eq(ID))).thenReturn(PROTECTION);

        mockMvc.perform(get(urlId, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<Protection> response = controller.findById(ID);

        assertNotNull(response);
        assertEquals(ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(BENEFITS, response.getBody().getBenefits());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(VALUE, response.getBody().getValue());
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteWithSuccess() throws Exception {
        doNothing().when(service).delete(eq(ID));

        mockMvc.perform(MockMvcRequestBuilders.delete(urlId, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        ResponseEntity<Void> response = controller.delete(ID);

        assertNotNull(response);
        verifyNoMoreInteractions(service);
    }

    @Test
    void whenUpdateThenReturnSuccess() throws Exception {
        when(service.update(any(), any())).thenReturn(RESPONSE_DTO);

        mockMvc.perform(put(urlId, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<ProtectionResponseDTO> response = controller.update(REQUEST_DTO, ID);

        assertNotNull(response);
        assertEquals(ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(BENEFITS, response.getBody().getBenefits());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(VALUE, response.getBody().getValue());
        verifyNoMoreInteractions(service);
    }

    private void startProtection(){
        RESPONSE_DTO = new ProtectionResponseDTO(ID, NAME, BENEFITS, VALUE);
        REQUEST_DTO = new ProtectionRequestDTO(NAME, BENEFITS, VALUE);
        PROTECTION = new Protection(ID, NAME, BENEFITS, VALUE);
    }
}