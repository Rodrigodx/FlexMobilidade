package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.dto.cars.CarsDTO;
import com.rodrigo.flexmobilidade.dto.categories.CategoryCarsRequestDTO;
import com.rodrigo.flexmobilidade.dto.categories.CategoryRequestDTO;
import com.rodrigo.flexmobilidade.dto.categories.CategoryResponseDTO;
import com.rodrigo.flexmobilidade.model.cars.Cars;
import com.rodrigo.flexmobilidade.model.categories.Category;
import com.rodrigo.flexmobilidade.repositories.CategoryRepository;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

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
    @InjectMocks
    private CategoryService service;

    @Mock
    private CarsService carsService;

    @Mock
    private CategoryRepository repository;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCategory();
    }

    @Test
    void whenSaveThenReturnSuccess() {
        when(repository.save(any())).thenReturn(CATEGORY);

        CategoryResponseDTO response = service.save(REQUEST_DTO);

        assertNotNull(response);
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
    }

    @Test
    void whenAddCarsThenReturnSuccess() {
        when(mapper.map(eq(CARS_REQUEST_DTO.getCarsList()), any())).thenReturn(List.of(CARS_DTO));
        when(repository.findById(eq(ID))).thenReturn(Optional.ofNullable(CATEGORY));
        when(mapper.map(any(), eq(Category.class))).thenReturn(CATEGORY);

        CategoryResponseDTO responseDTO = service.addCars(CARS_REQUEST_DTO, ID);

        verify(repository).findById(eq(ID));
        verify(repository).save(any());

        assertNotNull(responseDTO);
        assertEquals(CategoryResponseDTO.class, responseDTO.getClass());
        assertEquals(ID, responseDTO.getId());
        assertEquals(NAME, responseDTO.getName());
    }


    @Test
    void whenFindAllThenReturnSuccess() {
        when(repository.findAll()).thenReturn(List.of(CATEGORY));

        List<CategoryResponseDTO> responseDTO = service.findAll();

        verify(repository).findAll();
        assertNotNull(responseDTO);
        assertEquals(ArrayList.class, responseDTO.getClass());
        assertEquals(ID, responseDTO.get(INDEX).getId());
        assertEquals(NAME, responseDTO.get(INDEX).getName());
        assertEquals(CARS_LIST, responseDTO.get(INDEX).getCarsList());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(repository.findById(eq(ID))).thenReturn(Optional.ofNullable(CATEGORY));

        Category response = service.findById(ID);

        verify(repository).findById(ID);
        assertNotNull(response);
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CARS_LIST, response.getCarsList());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(eq(ID))).thenReturn(Optional.ofNullable(CATEGORY));
        doNothing().when(repository).deleteById(ID);

        service.delete(ID);

        verify(repository).deleteById(ID);
    }

    @Test
    void deleteWithNotSuccess() {
        when(repository.findById(eq(ID))).thenReturn(Optional.empty());

        try {
            service.delete(ID);
            fail();
        } catch (Exception e){
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals("Category with ID:" + ID + " not found", e.getMessage());
        }

    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.findById(eq(ID))).thenReturn(Optional.ofNullable(CATEGORY));
        when(repository.save(any())).thenReturn(CATEGORY);

        CategoryResponseDTO responseDTO = service.update(REQUEST_DTO, ID);

        assertNotNull(responseDTO);
        assertEquals(ID, responseDTO.getId());
        assertEquals(NAME, responseDTO.getName());
        assertEquals(CARS_LIST, responseDTO.getCarsList());
        verify(repository).save(any());
    }
    @Test
    void whenUpdateThenReturnNoSuchElementException() {
        when(repository.findById(eq(ID))).thenReturn(Optional.empty());

        try {
            service.update(REQUEST_DTO, ID);
            fail();
        }catch (Exception e){
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals("Category with ID:" + ID + " not found", e.getMessage());
        }
    }

    private void  startCategory(){
        CATEGORY = new Category(ID, NAME, CARS_LIST);
        CARS_DTO = new CarsDTO(ID);
        CARS_REQUEST_DTO = new CategoryCarsRequestDTO(CARS_DTO_LIST);
        RESPONSE_DTO = new CategoryResponseDTO(ID, NAME, CARS_LIST);
        REQUEST_DTO = new CategoryRequestDTO(NAME);
    }
}