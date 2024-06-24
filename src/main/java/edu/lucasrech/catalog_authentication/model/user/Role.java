package edu.lucasrech.catalog_authentication.model.user;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("admin"),
    USER("user");

    private String role;
    Role(String role) {
        this.role = role;
    }
}
