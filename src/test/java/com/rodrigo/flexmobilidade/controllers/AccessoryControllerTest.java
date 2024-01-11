package com.rodrigo.flexmobilidade.controllers;

import com.rodrigo.flexmobilidade.dto.accessories.AccessoryRequestDTO;
import com.rodrigo.flexmobilidade.dto.accessories.AccessoryResponseDTO;
import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.services.AccessoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
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
    void whenDeleteThenReturnSuccess() {
        doNothing().when(accessoryService).delete(anyInt());

        ResponseEntity<Void> response = accessoryController.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
        verify(accessoryService, times(1)).delete(anyInt());

    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(accessoryService.update(accessoryRequestDTO, ID)).thenReturn(accessoryResponseDTO);

        ResponseEntity<AccessoryResponseDTO> response = accessoryController.update(accessoryRequestDTO, ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(VALUES, response.getBody().getValues());

    }

    private void startAccessory(){
        accessory = new Accessory(ID, NAME, VALUES);
        accessoryRequestDTO = new AccessoryRequestDTO(NAME, VALUES);
        accessoryResponseDTO = new AccessoryResponseDTO(ID, NAME, VALUES);
    }
}