package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.dto.protections.ProtectionRequestDTO;
import com.rodrigo.flexmobilidade.dto.protections.ProtectionResponseDTO;
import com.rodrigo.flexmobilidade.model.protections.Protection;
import com.rodrigo.flexmobilidade.repositories.ProtectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProtectionServiceTest {

    public static final int ID = 1;
    public static final String NAME = "TestName";
    public static final String BENEFITS = "TestBenefits";
    public static final double VALUE = 0.0;
    public static final int INDEX = 0;
    public static Protection PROTECTION = new Protection(ID, NAME, BENEFITS, VALUE);
    public static ProtectionRequestDTO REQUEST_DTO = new ProtectionRequestDTO(NAME, BENEFITS, VALUE);
    public static ProtectionResponseDTO RESPONSE_DTO = new ProtectionResponseDTO(ID, NAME, BENEFITS, VALUE);

    @InjectMocks
    private ProtectionService service;

    @Mock
    private ProtectionRepository repository;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        startProtection();
    }


    @Test
    void whenSaveThenReturnSuccess() {
        when(repository.save(any())).thenReturn(PROTECTION);
        when(mapper.map(any(), any())).thenReturn(RESPONSE_DTO);

        ProtectionResponseDTO responseDTO = service.save(REQUEST_DTO);

        assertNotNull(responseDTO);
        assertEquals(ID, responseDTO.getId());
        assertEquals(NAME, responseDTO.getName());
        assertEquals(BENEFITS, responseDTO.getBenefits());
        assertEquals(VALUE, responseDTO.getValue());
        verify(repository, times(1)).save(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void whenFindAllThenReturnSuccess() {
        when(repository.findAll()).thenReturn(List.of(PROTECTION));
        when(mapper.map(any(), any())).thenReturn(List.of(RESPONSE_DTO));

        List<ProtectionResponseDTO> response = service.findAll();

        assertNotNull(response);
        assertEquals(ArrayList.class, response.getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(BENEFITS, response.get(INDEX).getBenefits());
        assertEquals(VALUE, response.get(INDEX).getValue());
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(repository.findById(eq(ID))).thenReturn(Optional.ofNullable(PROTECTION));

        Protection response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Protection.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(BENEFITS, response.getBenefits());
        assertEquals(VALUE, response.getValue());
        verify(repository, times(1)).findById(eq(ID));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(eq(ID))).thenReturn(Optional.ofNullable(PROTECTION));
        doNothing().when(repository).deleteById(eq(ID));

        service.delete(ID);

        verify(repository, times(1)).deleteById(eq(ID));
    }
    @Test
    void deleteWithNotSuccess() {
        when(repository.findById(eq(ID))).thenReturn(Optional.empty());

        try {
            service.delete(ID);
            fail();
        }catch (Exception e){
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals("Protection with ID:" + ID + " not found", e.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.findById(eq(ID))).thenReturn(Optional.ofNullable(PROTECTION));
        when(repository.save(any())).thenReturn(PROTECTION);
        when(mapper.map(any(), any())).thenReturn(RESPONSE_DTO);

        ProtectionResponseDTO responseDTO = service.update(REQUEST_DTO, ID);

        assertNotNull(responseDTO);
        assertEquals(ID, responseDTO.getId());
        assertEquals(NAME, responseDTO.getName());
        assertEquals(BENEFITS, responseDTO.getBenefits());
        assertEquals(VALUE, responseDTO.getValue());
        verify(repository, times(1)).save(any());
    }

    @Test
    void whenUpdateThenReturnNoSuchElementException() {
        when(repository.findById(eq(ID))).thenReturn(Optional.empty());

        try {
            service.update(REQUEST_DTO, ID);
            fail();
        }catch (Exception e){
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals("Protection with ID:" + ID + " not found", e.getMessage());
        }
    }

    private void startProtection(){
        RESPONSE_DTO = new ProtectionResponseDTO(ID, NAME, BENEFITS, VALUE);
        REQUEST_DTO = new ProtectionRequestDTO(NAME, BENEFITS, VALUE);
        PROTECTION = new Protection(ID, NAME, BENEFITS, VALUE);
    }
}