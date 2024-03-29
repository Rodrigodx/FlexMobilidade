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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
class AccessoryServiceTest {

    public static final int ID = 1;
    public static final String NAME = "Test";
    public static final double VALUES = 25.00;
    public static final int INDEX = 0;

    @InjectMocks
    private AccessoryService service;
    @Mock
    private AccessoryRepository accessoryRepository;

    @Mock
    private AccessoryRequestDTO accessoryRequestDTO;
    @Mock
    private AccessoryResponseDTO accessoryResponseDTO;
    @Mock
    private Accessory accessory;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startAccessory();
    }
    @Test
    void whenSaveThenReturnSuccess() {
        when(accessoryRepository.save(any())).thenReturn(accessory);

        accessoryResponseDTO = service.save(accessoryRequestDTO);

        assertNotNull(accessoryResponseDTO);
        assertEquals(AccessoryResponseDTO.class, accessoryResponseDTO.getClass());
        assertEquals(ID, accessoryResponseDTO.getId());
        assertEquals(NAME, accessoryResponseDTO.getName());
        assertEquals(VALUES, accessoryResponseDTO.getValues());
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

        accessory = service.findById(ID);

        assertNotNull(accessory);
        assertEquals(Accessory.class, accessory.getClass());
        assertEquals(ID, accessory.getId());
        assertEquals(NAME, accessory.getName());
        assertEquals(VALUES, accessory.getValues());
    }
    @Test
    void whenUpdateThenReturnSuccess(){
        when(accessoryRepository.findById(anyInt())).thenReturn(Optional.ofNullable(accessory));
        when(accessoryRepository.save(any())).thenReturn(accessory);

        accessoryResponseDTO = service.update(accessoryRequestDTO, ID);

        assertNotNull(accessoryResponseDTO);
        assertEquals(AccessoryResponseDTO.class, accessoryResponseDTO.getClass());
        assertEquals(ID, accessoryResponseDTO.getId());
        assertEquals(NAME, accessoryResponseDTO.getName());
        assertEquals(VALUES, accessoryResponseDTO.getValues());
    }
    @Test
    void whenUpdateThenReturnNoSushElementException(){
        when(accessoryRepository.findById(anyInt())).thenReturn(Optional.empty());

        try{
            service.update(accessoryRequestDTO, ID);
        }catch (NoSuchElementException e){
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals("Accessory with ID:" + ID + " not found", e.getMessage());
        }
    }

    @Test
    void deleteWithSuccess(){
        when(accessoryRepository.findById(eq(ID))).thenReturn(Optional.ofNullable(accessory));
        doNothing().when(accessoryRepository).deleteById(eq(ID));

        service.delete(ID);

        verify(accessoryRepository, times(1)).deleteById(eq(ID));
    }

    @Test
    void deleteWithNotSuccess(){
        when(accessoryRepository.findById(anyInt())).thenReturn(Optional.empty());
        try {
            service.delete(ID);
            fail("Expected NoSuchElementException was not thrown");
            verify(accessoryRepository, times(1)).deleteById(ID);
        }catch (NoSuchElementException e){
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals("Accessory with ID:" + ID + " not found", e.getMessage());
        }
    }

    private void startAccessory(){
        accessory = new Accessory(ID, NAME, VALUES);
        accessoryRequestDTO = new AccessoryRequestDTO(NAME, VALUES);
        accessoryResponseDTO = new AccessoryResponseDTO(ID, NAME, VALUES);
    }

}