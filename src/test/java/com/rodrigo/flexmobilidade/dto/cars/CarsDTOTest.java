package com.rodrigo.flexmobilidade.dto.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CarsDTOTest {

    public static final int ID = 1;
    private static CarsDTO CARSDTO_1 = new CarsDTO(ID);
    private static CarsDTO CARSDTO_2 = new CarsDTO(ID);
    private static CarsDTO CARSDTO_3 = new CarsDTO(2);
    private static CarsDTO CARSDTO_4 = new CarsDTO();

    private static String EXPECTED = "CarsDTO(id=1)";

    @InjectMocks
    private CarsDTO carsDTO;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getId() {
        carsDTO.setId(ID);
        Integer result = carsDTO.getId();
        assertEquals(ID, result);
    }

    @Test
    void setId() {
        carsDTO.setId(ID);
    }

    @Test
    void testEquals() {
        assertEquals(CARSDTO_1 ,CARSDTO_2);
        assertNotEquals(CARSDTO_1, CARSDTO_3);
    }

    @Test
    void canEqual() {
        assertTrue(CARSDTO_1.canEqual(CARSDTO_2));
    }

    @Test
    void testHashCode() {
        assertNotEquals(CARSDTO_1.hashCode(), CARSDTO_3.hashCode());
        assertEquals(CARSDTO_1.hashCode(), CARSDTO_2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(EXPECTED, CARSDTO_1.toString());
    }

    @Test
    void testNoArgsConstructor(){
        assertNotNull(CARSDTO_4 = new CarsDTO());
    }
}