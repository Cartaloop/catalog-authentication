package edu.lucasrech.catalog_authentication.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponseDTO(String message, HttpStatus status, String request) {
}
