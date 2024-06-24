package edu.lucasrech.catalog_authentication.model.product;


public record ProductDTO(String name, String description, double price, String image_url, Category category) {
}
