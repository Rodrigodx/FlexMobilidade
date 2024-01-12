package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.dto.cars.CarsRequestDTO;
import com.rodrigo.flexmobilidade.dto.cars.CarsResponseDTO;
import com.rodrigo.flexmobilidade.model.cars.Cars;
import com.rodrigo.flexmobilidade.repositories.CarsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarsServiceTest {

    public static final int ID = 1;
    public static final String MODEL = "test";
    public static final int INDEX = 0;
    private static  CarsResponseDTO response = new CarsResponseDTO(ID, MODEL);
    private static  CarsRequestDTO request = new CarsRequestDTO(MODEL);
    private static  Cars cars = new Cars(ID, MODEL);
    @InjectMocks
    private CarsService service;
    @Mock
    private CarsRepository repository;
    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        starCars();
    }

    @Test
    void whenSaveThenReturnSuccess() {
        when(repository.save(any())).thenReturn(cars);
        lenient().when(mapper.map(request, Cars.class)).thenReturn(cars);
        lenient().when(mapper.map(cars, CarsResponseDTO.class)).thenReturn(response);

        CarsResponseDTO responseDTO = service.save(request);

        assertNotNull(responseDTO);
        assertEquals(CarsResponseDTO.class, response.getClass());

        assertEquals(ID, responseDTO.getId());
        assertEquals(MODEL, responseDTO.getModel());

        verify(repository).save(any());
        verifyNoMoreInteractions(repository);

    }

    @Test
    void whenFindAllThenReturnSuccess() {
        when(repository.findAll()).thenReturn((List.of(cars)));
        lenient().when(mapper.map(any(), any())).thenReturn(List.of(response));

        List<CarsResponseDTO> responseDTO = service.findAll();

        assertNotNull(responseDTO);
        assertEquals(CarsResponseDTO.class, responseDTO.get(INDEX).getClass());

        assertEquals(ID, responseDTO.get(INDEX).getId());
        assertEquals(MODEL, responseDTO.get(INDEX).getModel());

        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(repository.findById(eq(ID))).thenReturn(Optional.ofNullable(cars));

        Cars response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Cars.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(MODEL, response.getModel());

        verify(repository).findById(ID);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteWithSuccess(){
        when(repository.findById(eq(ID))).thenReturn(Optional.ofNullable(cars));
        doNothing().when(repository).deleteById(eq(ID));

        service.delete(ID);

        verify(repository, times(1)).deleteById(ID);
    }

    @Test
    void deleteWithNotSuccess(){
        when(repository.findById(eq(ID))).thenReturn(Optional.empty());

        try {
            service.delete(ID);
            fail();
        }catch (Exception e ){
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals("Cars with ID:" + ID + " not found", e.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess(){
        when(repository.findById(eq(ID))).thenReturn(Optional.ofNullable(cars));
        lenient().when(repository.save(any())).thenReturn(cars);
        lenient().when(mapper.map(any(), any())).thenReturn(response);

        CarsResponseDTO responseDTO = service.update(request, ID);

        assertNotNull(responseDTO);

        assertEquals(ID, responseDTO.getId());
        assertEquals(MODEL, responseDTO.getModel());

        verify(repository).save(any());
    }
    @Test
    void whenUpdateThenReturnNoSuchElementeException(){
        when(repository.findById(eq(ID))).thenReturn(Optional.empty());

        try {
            service.update(request, ID);
            fail();
        }catch (Exception e){
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals("Cars with ID:" + ID + " not found", e.getMessage());
        }
    }

    private void starCars(){
        response = new CarsResponseDTO(ID, MODEL);
        request = new CarsRequestDTO(MODEL);
        cars = new Cars(ID, MODEL);

    }
}