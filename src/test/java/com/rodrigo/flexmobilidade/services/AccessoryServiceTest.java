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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class AccessoryServiceTest {

    public static final int ID = 1;
    public static final String NAME = "Acento criança";
    public static final double VALUES = 25.00;
    public static final int INDEX = 0;

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
    void whenSaveThenReturnSuccess() {
        when(accessoryRepository.save(any())).thenReturn(accessory);

        AccessoryResponseDTO response = service.save(accessoryRequestDTO);

        assertNotNull(response);
        assertEquals(AccessoryResponseDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(VALUES, response.getValues());
    }

    @Test
    void whenFindAllThenReturnAnListOfAccessories() {
        when(accessoryRepository.findAll()).thenReturn(List.of(accessory));

        List<AccessoryResponseDTO> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(AccessoryResponseDTO.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(VALUES, response.get(INDEX).getValues());
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