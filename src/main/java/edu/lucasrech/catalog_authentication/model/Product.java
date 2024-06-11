package edu.lucasrech.catalog_authentication.model;

import edu.lucasrech.catalog_authentication.model.enums.Category;
import jakarta.persistence.*;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String description;
    private double price;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Category category;
}
