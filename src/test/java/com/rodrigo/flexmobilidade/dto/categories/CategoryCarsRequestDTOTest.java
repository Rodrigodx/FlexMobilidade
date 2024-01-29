package com.rodrigo.flexmobilidade.dto.categories;

import com.rodrigo.flexmobilidade.dto.cars.CarsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCarsRequestDTOTest {

    @InjectMocks
    private CategoryCarsRequestDTO categoryCarsRequestDTO;

    private static List<CarsDTO> carsDTOS = new ArrayList<>();
    private static List<CarsDTO> carsDTOS_1 = new ArrayList<>();
    private static CategoryCarsRequestDTO CATEROGY_CARS_1 = new CategoryCarsRequestDTO(carsDTOS);
    private static CategoryCarsRequestDTO CATEROGY_CARS_2 = new CategoryCarsRequestDTO(carsDTOS);

    private static String EXPECTED = "CategoryCarsRequestDTO(carsList=[CarsDTO(id=1)])";
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCarsList() {
        categoryCarsRequestDTO.setCarsList(carsDTOS);
        assertEquals(carsDTOS, categoryCarsRequestDTO.getCarsList());
    }

    @Test
    void setCarsList() {
        categoryCarsRequestDTO.setCarsList(carsDTOS);
    }

    @Test
    void testEquals() {
        assertEquals(CATEROGY_CARS_1, CATEROGY_CARS_2);
    }

    @Test
    void canEqual() {
        assertTrue(CATEROGY_CARS_1.canEqual(CATEROGY_CARS_2));
    }

    @Test
    void testToString() {
        CarsDTO carsDTO = new CarsDTO(1);
        CategoryCarsRequestDTO categoryCarsRequestDTO_1 = new CategoryCarsRequestDTO();
        categoryCarsRequestDTO_1.getCarsList().add(carsDTO);
        String result = categoryCarsRequestDTO_1.toString();
        assertEquals(result, EXPECTED);
    }
}