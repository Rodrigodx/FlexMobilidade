package com.rodrigo.flexmobilidade.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {
    private final Constants constants = new Constants();
    private static Constants CONSTANTS = new Constants();
    private static Constants CONSTANTS_1 = new Constants();
    private static String EXPECTED = "Constants()";

    @Test
    void testConstantsValues() {
        assertEquals("Unable to process something went wrong !..", Constants.UNABLE_TO_PROCESS);
        assertEquals("200", Constants.SUCCESS_CODE);
        assertEquals("201", Constants.CREATED);
        assertEquals("400", Constants.ERROR_CODE);
        assertEquals("New user registration Completed", Constants.NEW_USER);
        assertEquals("Success", Constants.SUCCESS);
        assertEquals("Activated", Constants.ACTIVATED);
        assertEquals("Deactivated", Constants.DEACTIVATED);
        assertEquals("User deactivated Successfully", Constants.USER_DEACTIVATED);
    }

    @Test
    void testEquals() {
        assertEquals(CONSTANTS, CONSTANTS_1);
    }

    @Test
    void canEqual() {
        assertTrue(CONSTANTS.canEqual(CONSTANTS_1));
    }

    @Test
    void testHashCode() {
        assertEquals(CONSTANTS.hashCode(), CONSTANTS_1.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(EXPECTED, CONSTANTS.toString());
    }
}