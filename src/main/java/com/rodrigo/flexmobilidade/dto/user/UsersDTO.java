package com.rodrigo.flexmobilidade.dto.user;

import com.rodrigo.flexmobilidade.model.user.UserRole;

import lombok.Getter;

@Getter
public class UsersDTO {
    private String name;
    private String email;
    private String password;
    private UserRole role;

    public UsersDTO(String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
