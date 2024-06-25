package edu.lucasrech.catalog_authentication.model.user.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("admin");

    private final String role;
    Role(String role) {
        this.role = role;
    }
}
