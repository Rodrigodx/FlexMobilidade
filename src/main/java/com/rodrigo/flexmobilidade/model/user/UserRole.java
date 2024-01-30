package com.rodrigo.flexmobilidade.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum UserRole {
    USER("user"),

    ADMIN("admin");

    private String role;

    UserRole(String role){
        this.role = role;
    }

}
