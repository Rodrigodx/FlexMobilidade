package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.dto.additonalutility.AdditionalUtilityRequestDTO;
import com.rodrigo.flexmobilidade.dto.additonalutility.AdditionalUtilityResponseDTO;
import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.repositories.AdditionalUtilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AdditionalUtilityServiceTest {

    public static final int ID = 1;
    public static final int QUANTITY = 0;
    public static final String NAME = "Test";
    public static final double VALUE = 0.0;
    public static final int INDEX = 0;
    public static AdditionalUtilityRequestDTO utilityRequest = new AdditionalUtilityRequestDTO(NAME, VALUE, QUANTITY);
    public static AdditionalUtilityResponseDTO utilityResponse = new AdditionalUtilityResponseDTO(ID, NAME, VALUE, QUANTITY);
    public static AdditionalUtility utility = new AdditionalUtility(ID, NAME, VALUE, QUANTITY);

    @InjectMocks
    private AdditionalUtilityService service;
    @Mock
    private AdditionalUtilityRepository repository;
    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startAdditionalUtility();
    }

    @Test
    void whenSaveThenReturnSuccess() {
        when(repository.save(any())).thenReturn(utility);
        when(mapper.map(any(), any())).thenReturn(utilityResponse);

        AdditionalUtilityResponseDTO utilityResponse = service.save(utilityRequest);
        utilityResponse.setQuantity(QUANTITY);

        assertNotNull(utilityResponse);
        assertEquals(AdditionalUtilityResponseDTO.class, utilityResponse.getClass());

        assertEquals(ID, utilityResponse.getId());
        assertEquals(NAME, utilityResponse.getName());
        assertEquals(VALUE, utilityResponse.getValue());
        assertEquals(QUANTITY, utilityResponse.getQuantity());
    }

    @Test
    void whenFindAllThenReturnSuccess() {
        when(repository.findAll()).thenReturn(List.of(utility));
        when(mapper.map(any(), any())).thenReturn(utilityResponse);

        List<AdditionalUtilityResponseDTO> response = service.findAll();

        assertNotNull(response);
        assertEquals(AdditionalUtilityResponseDTO.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(VALUE, response.get(INDEX).getValue());
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(repository.findById(eq(ID))).thenReturn(Optional.ofNullable(utility));

        AdditionalUtility utility = service.findById(ID);
        utility.setQuantity(0);

        assertNotNull(utility);
        assertEquals(AdditionalUtility.class, utility.getClass());

        assertEquals(ID, utility.getId());
        assertEquals(NAME, utility.getName());
        assertEquals(VALUE, utility.getValue());
        assertEquals(QUANTITY, utility.getQuantity());
    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(eq(ID))).thenReturn(Optional.ofNullable(utility));
        doNothing().when(repository).deleteById(eq(ID));

        service.delete(ID);

        verify(repository, times(1)).deleteById(eq(ID));
    }

    @Test
    void deleteWithNotSuccess(){
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        try {
            service.delete(ID);
            fail("Expected NoSuchElementException was not thrown");
            verify(repository, times(1)).deleteById(ID);
        }catch (Exception e){
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals(("AdditionalUtility with ID:" + ID + " not found"), e.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.findById(eq(ID))).thenReturn(Optional.ofNullable(utility));
        when(repository.save(any())).thenReturn(utility);
        when(mapper.map(any(), any())).thenReturn(utilityResponse);

        AdditionalUtilityResponseDTO utilityResponse = service.update(utilityRequest, ID);
        utilityResponse.setQuantity(QUANTITY);

        assertEquals(ID, utilityResponse.getId());
        assertEquals(NAME, utilityResponse.getName());
        assertEquals(VALUE, utilityResponse.getValue());
        assertEquals(QUANTITY, utilityResponse.getQuantity());
    }

    @Test
    void whenUpdateThenReturnNoSuchElementException(){
        when(repository.findById(eq(ID))).thenReturn(Optional.empty());

        try {
            service.update(utilityRequest, ID);
            fail("Expected NoSuchElementException was not thrown");
        }catch (Exception e){
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals("AdditionalUtility with ID:" + ID + " not found", e.getMessage());
        }
    }

    private void startAdditionalUtility(){
        utility = new AdditionalUtility(ID, NAME, VALUE, ID);
        utilityResponse = new AdditionalUtilityResponseDTO(ID, NAME, VALUE, ID);
        utilityRequest = new AdditionalUtilityRequestDTO(NAME, VALUE, ID);
    }
}