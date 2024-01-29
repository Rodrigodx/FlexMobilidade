package com.rodrigo.flexmobilidade.dto.additonalutility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AdditionalUtilityDTOTest {

    public static final int ID = 1;
    private static AdditionalUtilityDTO ADDITIONAL_1 = new AdditionalUtilityDTO(ID);
    private static AdditionalUtilityDTO ADDITIONAL_2 = new AdditionalUtilityDTO(ID);
    private static AdditionalUtilityDTO ADDITIONAL_3 = new AdditionalUtilityDTO(2);
    private static AdditionalUtilityDTO ADDITIONAL_4 = new AdditionalUtilityDTO();
    private static String EXPECTED = "AdditionalUtilityDTO(id=1)";

    @InjectMocks
    private AdditionalUtilityDTO additionalUtilityDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getId() {
        additionalUtilityDTO.setId(ID);
        Integer result = additionalUtilityDTO.getId();
        assertEquals(ID, result);
    }

    @Test
    void setId() {
        additionalUtilityDTO.setId(ID);
    }

    @Test
    void testEquals() {
        assertEquals(ADDITIONAL_1, ADDITIONAL_2);
        assertNotEquals(ADDITIONAL_1, ADDITIONAL_3);
    }

    @Test
    void canEqual() {
        assertTrue(ADDITIONAL_1.canEqual(ADDITIONAL_2));
    }

    @Test
    void testHashCode() {
        assertNotEquals(ADDITIONAL_1.hashCode(), ADDITIONAL_3.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(EXPECTED, ADDITIONAL_1.toString());
    }

    @Test
    void testNorArgsConstructor(){
         assertNotNull(ADDITIONAL_4);
    }
}