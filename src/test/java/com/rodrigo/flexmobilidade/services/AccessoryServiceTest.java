package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.dto.accessories.AccessoryRequestDTO;
import com.rodrigo.flexmobilidade.dto.accessories.AccessoryResponseDTO;
import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.repositories.AccessoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class AccessoryServiceTest {

    public static final int ID = 1;
    public static final String NAME = "Acento criança";
    public static final double VALUES = 25.00;
    @InjectMocks
    private AccessoryService service;
    @Mock
    private AccessoryRepository accessoryRepository;
    @Mock
    private ModelMapper modelMapper = new ModelMapper();

    private AccessoryRequestDTO accessoryRequestDTO;
    private AccessoryResponseDTO accessoryResponseDTO;
    private Accessory accessory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startAccessory();
    }

    @Test
    void save() {
    }

    @Test
    void findAll() {
    }

    @Test
    void whenFindByIdThenReturnAnInstanceAccessory() {
        when(accessoryRepository.findById(anyInt())).thenReturn(Optional.ofNullable(accessory));

        Accessory response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Accessory.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(VALUES, response.getValues());
    }
    private void startAccessory(){
        accessory = new Accessory(ID, NAME, VALUES);
        accessoryRequestDTO = new AccessoryRequestDTO(NAME, VALUES);
        accessoryResponseDTO = new AccessoryResponseDTO(ID, NAME, VALUES);
    }
}