package edu.lucasrech.catalog_authentication.model.user;

public record RegisterRequestDTO(String username, String email, String password, Role role) {
}
