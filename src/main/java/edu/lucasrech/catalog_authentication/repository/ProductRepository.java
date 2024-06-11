package edu.lucasrech.catalog_authentication.repository;

import edu.lucasrech.catalog_authentication.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
