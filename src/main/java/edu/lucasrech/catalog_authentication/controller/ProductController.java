package edu.lucasrech.catalog_authentication.controller;

import edu.lucasrech.catalog_authentication.model.Product;
import edu.lucasrech.catalog_authentication.model.enums.Category;
import edu.lucasrech.catalog_authentication.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(productService.getProductsByCategory(category));
    }

    @GetMapping("/name")
    public ResponseEntity<List<Product>> getProductByName(@RequestParam String name) {
        return ResponseEntity.ok().body(productService.searchByName(name));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.createProduct(product));
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody String id, Product product) {
        return ResponseEntity.ok().body(productService.updateProduct(id, product));
    }

    @DeleteMapping
    public ResponseEntity<Product> deleteProduct(@RequestBody String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}
