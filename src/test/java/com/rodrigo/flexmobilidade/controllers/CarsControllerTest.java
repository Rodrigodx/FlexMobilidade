package com.rodrigo.flexmobilidade.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigo.flexmobilidade.dto.cars.CarsRequestDTO;
import com.rodrigo.flexmobilidade.dto.cars.CarsResponseDTO;
import com.rodrigo.flexmobilidade.model.cars.Cars;
import com.rodrigo.flexmobilidade.services.CarsService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CarsControllerTest {

    public static final int INDEX = 0;
    public static  int ID = 1;
    public static  String MODEL = "Test";
    public static  Cars CARS = new Cars(ID, MODEL);
    public static  CarsRequestDTO REQUEST = new CarsRequestDTO(MODEL);
    public static  CarsResponseDTO RESPONSE = new CarsResponseDTO(ID, MODEL);

    private MockMvc mockMvc;
    private String json;
    private String url;
    private String urlId;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private CarsController controller;
    @Mock
    private CarsService service;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
        url = "/api/cars";
        urlId = "/api/cars/{id}";
        json = objectMapper.writeValueAsString(REQUEST);
        startCars();
    }

    @Test
    void whenSaveThenReturnSuccess() throws Exception{
        when(service.save(REQUEST)).thenReturn(RESPONSE);

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        ResponseEntity<CarsResponseDTO> response = controller.save(REQUEST);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());

        assertEquals(ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(MODEL, response.getBody().getModel());

        verifyNoMoreInteractions(service);
    }

    @Test
    void whenFindAllThenReturnSuccess() throws Exception {
        when(service.findAll()).thenReturn(List.of(RESPONSE));

        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<List<CarsResponseDTO>> response = controller.findAll();

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());

        assertEquals(ID, Objects.requireNonNull(response.getBody()).get(INDEX).getId());
        assertEquals(MODEL, response.getBody().get(INDEX).getModel());

        verifyNoMoreInteractions(service);
    }

    @Test
    void whenFindByIdThenReturnSuccess() throws Exception{
        when(service.findById(eq(ID))).thenReturn(CARS);

        mockMvc.perform(get(urlId, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<Cars> response = controller.findById(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());

        assertEquals(ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(MODEL, response.getBody().getModel());

        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteWithSuccess() throws Exception{
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
    void thenUpdateThenReturnSuccess() throws Exception{
        when(service.update(any(), any())).thenReturn(RESPONSE);

        mockMvc.perform(put(urlId, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<CarsResponseDTO> response = controller.update(REQUEST, ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());

        assertEquals(ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(MODEL, response.getBody().getModel());

    }

    private void startCars(){
        CARS = new Cars(ID, MODEL);
        RESPONSE = new CarsResponseDTO(ID, MODEL);
        REQUEST = new CarsRequestDTO(MODEL);

    }
}