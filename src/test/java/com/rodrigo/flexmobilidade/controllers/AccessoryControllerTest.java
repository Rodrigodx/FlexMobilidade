package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.dto.accessories.AccessoryRequestDTO;
import com.rodrigo.flexmobilidade.dto.accessories.AccessoryResponseDTO;
import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.services.AccessoryService;
import org.apache.coyote.Response;
import org.hibernate.annotations.Immutable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class AccessoryControllerTest {
    public static final int ID = 1;
    public static final String NAME = "Test";
    public static final double VALUES = 25.00;
    public static final int INDEX = 0;
    private AccessoryRequestDTO accessoryRequestDTO;
    private AccessoryResponseDTO accessoryResponseDTO;
    private Accessory accessory;

    @InjectMocks
    private AccessoryController accessoryController;

    @Mock
    private AccessoryService accessoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startAccessory();
    }

    @Test
    void whenSaveThenReturnSuccess() {
        when(accessoryService.save(any())).thenReturn(accessoryResponseDTO);

        ResponseEntity<AccessoryResponseDTO> response = accessoryController.save(accessoryRequestDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(AccessoryResponseDTO.class, response.getBody().getClass());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void whenFindAllThenReturnSuccess() {
        when(accessoryService.findAll()).thenReturn(List.of(accessoryResponseDTO));

        ResponseEntity<List<AccessoryResponseDTO>> response = accessoryController.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(AccessoryResponseDTO.class, response.getBody().get(INDEX).getClass());
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(accessoryService.findById(eq(ID))).thenReturn(accessory);

        ResponseEntity<Accessory> response = accessoryController.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(VALUES, response.getBody().getValues());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    private void startAccessory(){
        accessory = new Accessory(ID, NAME, VALUES);
        accessoryRequestDTO = new AccessoryRequestDTO(NAME, VALUES);
        accessoryResponseDTO = new AccessoryResponseDTO(ID, NAME, VALUES);
    }
}