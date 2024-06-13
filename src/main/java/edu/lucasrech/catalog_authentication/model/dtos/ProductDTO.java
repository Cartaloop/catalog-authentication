package edu.lucasrech.catalog_authentication.model.dtos;

import edu.lucasrech.catalog_authentication.model.enums.Category;

public record ProductDTO(String name, String description, double price, String imageUrl, Category category) {
}
