package com.rodrigo.flexmobilidade.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigo.flexmobilidade.dto.additonalutility.AdditionalUtilityRequestDTO;
import com.rodrigo.flexmobilidade.dto.additonalutility.AdditionalUtilityResponseDTO;
import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.services.AdditionalUtilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AdditionalUtilityControllerTest {

    public static final int ID = 1;
    public static final int QUANTITY = 0;
    public static final String NAME = "Test";
    public static final double VALUE = 0.0;
    public static final int INDEX = 0;
    public static AdditionalUtilityRequestDTO utilityRequest = new AdditionalUtilityRequestDTO(NAME, VALUE, ID);
    public static AdditionalUtilityResponseDTO utilityResponse = new AdditionalUtilityResponseDTO(ID, NAME, VALUE, ID);
    public static AdditionalUtility utility = new AdditionalUtility(ID, NAME, VALUE, QUANTITY);

    private MockMvc mockMvc;
    private String json;
    private String url;
    private String urlId;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private AdditionalUtilityController controller;
    @Mock
    private AdditionalUtilityService service;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
        url = "/api/utility";
        urlId = "/api/utility/{id}";
        json = objectMapper.writeValueAsString(utilityRequest);
        startAdditionalUtility();
    }

    @Test
    void whenSaveThenReturnSuccess() throws Exception {
        when(service.save(utilityRequest)).thenReturn(utilityResponse);

        ResponseEntity<AdditionalUtilityResponseDTO> response = controller.save(utilityRequest);

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());

        assertEquals(ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(VALUE, response.getBody().getValue());
        assertEquals(QUANTITY, response.getBody().getQuantity());
    }

    @Test
    void whenSaveThenReturnNotSuccess() throws Exception {

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(service);
    }

    @Test
    void whenFindAllReturnSuccess() throws Exception {
        when(service.findAll()).thenReturn(List.of(utilityResponse));

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<List<AdditionalUtilityResponseDTO>> response = controller.findAll();

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ID, Objects.requireNonNull(response.getBody()).get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(VALUE, response.getBody().get(INDEX).getValue());
        assertEquals(QUANTITY, response.getBody().get(INDEX).getQuantity());
    }

    @Test
    void whenFindByIdThenReturnSuccess() throws Exception {
        when(service.findById(eq(ID))).thenReturn(utility);

        mockMvc.perform(get(urlId, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<AdditionalUtility> response = controller.findById(ID);
        utility.setQuantity(0);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(VALUE, response.getBody().getValue());
        assertEquals(QUANTITY, response.getBody().getQuantity());
        verifyNoMoreInteractions(service);
    }

    @Test
    void DeleteWithSuccess() throws Exception {
        doNothing().when(service).delete(eq(ID));

        mockMvc.perform(delete(urlId, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        ResponseEntity<Void> response = controller.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verifyNoMoreInteractions(service);
    }

    @Test
    void whenUpdateThenReturnSuccess() throws Exception {
        when(service.update(any(), any())).thenReturn(utilityResponse);

        mockMvc.perform(put(urlId , ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<AdditionalUtilityResponseDTO> response = controller.update(utilityRequest, ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(VALUE, response.getBody().getValue());
        assertEquals(QUANTITY, response.getBody().getQuantity());
    }

    private void startAdditionalUtility(){
        utility = new AdditionalUtility(ID, NAME, VALUE, ID);
        utilityResponse = new AdditionalUtilityResponseDTO(ID, NAME, VALUE, ID);
        utilityRequest = new AdditionalUtilityRequestDTO(NAME, VALUE, ID);
    }
}