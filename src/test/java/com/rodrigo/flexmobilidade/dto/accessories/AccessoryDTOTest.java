package com.rodrigo.flexmobilidade.dto.accessories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AccessoryDTOTest {

    public static final AccessoryDTO ACCESSORY_DTO_1 = new AccessoryDTO(1);
    public static final AccessoryDTO ACCESSORY_DTO_2 = new AccessoryDTO(1);
    public static final AccessoryDTO ACCESSORY_DTO_3 = new AccessoryDTO(2);
    public static final String EXPECTED = "AccessoryDTO(id=1)";
    private Integer id = 1;

    @InjectMocks
    public AccessoryDTO accessoryDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void setIdAndGetId() {
        accessoryDTO.setId(id);
        Integer result = accessoryDTO.getId();
        assertEquals(id, result, "O ID configurado deve ser igual ao ID obtido");
    }

    @Test
    void testEquals() {
        assertEquals(ACCESSORY_DTO_1, ACCESSORY_DTO_2);
        assertNotEquals(ACCESSORY_DTO_1, ACCESSORY_DTO_3);
    }

    @Test
    void canEqual() {
        assertTrue(ACCESSORY_DTO_1.canEqual(ACCESSORY_DTO_2));
    }

    @Test
    void testHashCode() {
        assertEquals(ACCESSORY_DTO_1.hashCode(), ACCESSORY_DTO_2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(EXPECTED, ACCESSORY_DTO_1.toString());
    }

    @Test
    void testNoArgsConstructor() {
        AccessoryDTO accessoryDTO = new AccessoryDTO();
        assertNotNull(accessoryDTO);
    }

    @Test
    void testAllArgsConstructor() {
        AccessoryDTO accessoryDTO = new AccessoryDTO(1);
        assertNotNull(accessoryDTO);
        assertEquals(1, accessoryDTO.getId());
    }
}