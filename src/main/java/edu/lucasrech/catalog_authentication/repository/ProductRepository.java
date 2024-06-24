package edu.lucasrech.catalog_authentication.repository;

import edu.lucasrech.catalog_authentication.model.product.Product;
import edu.lucasrech.catalog_authentication.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:productName%")
     List<Product> searchFilterByName(String productName);

     List<Product> findByCategory(Category category);

}
