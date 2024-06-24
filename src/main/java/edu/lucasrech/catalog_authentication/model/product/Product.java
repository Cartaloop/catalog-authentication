package edu.lucasrech.catalog_authentication.model.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lucasrech.catalog_authentication.model.product.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode()
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(name = "image_url", nullable = false)
    @JsonProperty("image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    public Product (ProductDTO productDTO) {
        this.id = UUID.randomUUID();
        this.name = productDTO.name();
        this.description = productDTO.description();
        this.price = productDTO.price();
        this.imageUrl = productDTO.image_url();
        this.category = productDTO.category();
    }


}
