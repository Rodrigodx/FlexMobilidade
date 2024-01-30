package com.rodrigo.flexmobilidade.dto.categories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDTOTest {

    public static final int ID = 1;
    public static final CategoryDTO CATEGORY_DTO_1 = new CategoryDTO(ID);
    public static final CategoryDTO CATEGORY_DTO_2 = new CategoryDTO(ID);
    public static final CategoryDTO CATEGORY_DTO_3 = new CategoryDTO();
    public static final String EXPECTED = "CategoryDTO(id=1)";

    @InjectMocks
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIdAndSetId() {
        categoryDTO.setId(ID);
        Integer result = categoryDTO.getId();
        assertEquals(ID, result);
    }

    @Test
    void testEquals() {
        assertEquals(CATEGORY_DTO_1, CATEGORY_DTO_2);
    }

    @Test
    void canEqual() {
        assertTrue(CATEGORY_DTO_1.canEqual(CATEGORY_DTO_2));
    }

    @Test
    void testHashCode() {
        assertEquals(CATEGORY_DTO_1.hashCode(), CATEGORY_DTO_2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(EXPECTED, CATEGORY_DTO_1.toString());
    }

    @Test
    void testNoArgsConstructor(){
        assertNotNull(CATEGORY_DTO_3);
    }
}