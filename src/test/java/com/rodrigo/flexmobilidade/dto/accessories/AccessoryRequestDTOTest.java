package com.rodrigo.flexmobilidade.dto.accessories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AccessoryRequestDTOTest {
    public static final AccessoryRequestDTO ACCESSORY_DTO_1 = new AccessoryRequestDTO("test", 1.0);
    public static final AccessoryRequestDTO ACCESSORY_DTO_2 = new AccessoryRequestDTO("test", 1.0);
    public static final AccessoryRequestDTO ACCESSORY_DTO_3 = new AccessoryRequestDTO("test2", 2.0);
    public static final String EXPECTED = "AccessoryRequestDTO(name=test, values=1.0)";
    public static final String NAME = "test";
    public static final Double VALUES = 1.0;


    @InjectMocks
    private AccessoryRequestDTO accessoryDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getName() {
      accessoryDTO.setName(NAME);
      String result = accessoryDTO.getName();
      assertEquals(NAME, result);
    }

    @Test
    void getValues() {
        accessoryDTO.setValues(VALUES);
        Double result = accessoryDTO.getValues();
        assertEquals(VALUES, result);
    }

    @Test
    void setName() {
        accessoryDTO.setName(NAME);
    }

    @Test
    void setValues() {
    }

    @Test
    void testEquals() {
        assertEquals(ACCESSORY_DTO_1, ACCESSORY_DTO_2);
    }

    @Test
    void canEqual() {
        assertTrue(ACCESSORY_DTO_1.equals(ACCESSORY_DTO_2));
    }

    @Test
    void testHashCode() {
        assertNotEquals(ACCESSORY_DTO_1.hashCode(), ACCESSORY_DTO_3.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(EXPECTED, ACCESSORY_DTO_1.toString());
    }
}