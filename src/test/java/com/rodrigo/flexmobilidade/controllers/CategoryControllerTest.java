package com.rodrigo.flexmobilidade.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.rodrigo.flexmobilidade.dto.cars.CarsDTO;
import com.rodrigo.flexmobilidade.dto.categories.CategoryCarsRequestDTO;
import com.rodrigo.flexmobilidade.dto.categories.CategoryRequestDTO;
import com.rodrigo.flexmobilidade.dto.categories.CategoryResponseDTO;
import com.rodrigo.flexmobilidade.model.cars.Cars;
import com.rodrigo.flexmobilidade.model.categories.Category;
import com.rodrigo.flexmobilidade.services.CategoryService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    public static final int ID = 1;
    public static final int INDEX = 0;
    public static CarsDTO CARS_DTO = new CarsDTO(ID);
    public static final List<CarsDTO> CARS_DTO_LIST = List.of(CARS_DTO);
    public static CategoryCarsRequestDTO CARS_REQUEST_DTO = new CategoryCarsRequestDTO(CARS_DTO_LIST);
    public static final String NAME = "Test";
    public static final List<Cars> CARS_LIST = List.of(new Cars(1, "Test"));
    public static Category CATEGORY = new Category(ID, NAME, CARS_LIST);
    public static CategoryRequestDTO REQUEST_DTO = new CategoryRequestDTO(NAME);
    public static CategoryResponseDTO RESPONSE_DTO = new CategoryResponseDTO(ID, NAME, CARS_LIST);
    private String json;
    private MockMvc mockMvc;
    private String url;
    private String urlId;

    private ObjectMapper objectMapper = new ObjectMapper();


    @InjectMocks
    private CategoryController controller;
    @Mock
    private CategoryService service;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
        url = "/api/category";
        urlId = "/api/category/{id}";
        json = objectMapper.writeValueAsString(REQUEST_DTO);
        startCategory();
    }

    @Test
    void whenSaveThenReturnSuccess() throws Exception {
        when(service.save(any())).thenReturn(RESPONSE_DTO);

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        ResponseEntity<CategoryResponseDTO> response = controller.save(REQUEST_DTO);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(CARS_LIST, response.getBody().getCarsList());
        verifyNoMoreInteractions(service);
    }

    @Test
    void whenAddCarsThenReturnSuccess() throws Exception {
        when(service.addCars(any(), any())).thenReturn(RESPONSE_DTO);

        mockMvc.perform(put(urlId, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<CategoryResponseDTO> response = controller.addCars(CARS_REQUEST_DTO, ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ID, Objects.requireNonNull(response.getBody()).getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(CARS_LIST, response.getBody().getCarsList());
    }

    @Test
    void whenFindAllThenReturnSuccess() throws Exception {
        when(service.findAll()).thenReturn(List.of(RESPONSE_DTO));

        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<List<CategoryResponseDTO>> response = controller.findAll();

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ID, Objects.requireNonNull(response.getBody()).get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(CARS_LIST, response.getBody().get(INDEX).getCarsList());
        verifyNoMoreInteractions(service);
    }

    @Test
    void whenFindByIdThenReturnSuccess() throws Exception {
        when(service.findById(eq(ID))).thenReturn(CATEGORY);

        mockMvc.perform(get(urlId, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<Category> response = controller.findById(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(CARS_LIST, response.getBody().getCarsList());
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteWithSuccess() throws Exception {
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
        when(service.update(any(), any())).thenReturn(RESPONSE_DTO);

        mockMvc.perform(put(urlId, ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ResponseEntity<CategoryResponseDTO> response = controller.update(REQUEST_DTO, ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(CARS_LIST, response.getBody().getCarsList());
        verifyNoMoreInteractions(service);
    }

    private void  startCategory(){
        CATEGORY = new Category(ID, NAME, CARS_LIST);
        CARS_DTO = new CarsDTO(ID);
        CARS_REQUEST_DTO = new CategoryCarsRequestDTO(CARS_DTO_LIST);
        RESPONSE_DTO = new CategoryResponseDTO(ID, NAME, CARS_LIST);
        REQUEST_DTO = new CategoryRequestDTO(NAME);
    }
}