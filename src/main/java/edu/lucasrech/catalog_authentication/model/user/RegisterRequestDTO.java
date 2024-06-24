package edu.lucasrech.catalog_authentication.model.user;

import edu.lucasrech.catalog_authentication.model.user.enums.Role;

public record RegisterRequestDTO(String username, String email, String password, Role role) {
}
