package com.rodrigo.flexmobilidade.model.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleTest {
    private UserRole role;

    @Test
    void getRole() {
        role = UserRole.USER;
        String result = role.getRole();
        assertEquals("user", result);
    }

}