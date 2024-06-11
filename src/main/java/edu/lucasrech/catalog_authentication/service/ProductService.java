package edu.lucasrech.catalog_authentication.service;

import edu.lucasrech.catalog_authentication.model.Product;
import edu.lucasrech.catalog_authentication.model.enums.Category;
import edu.lucasrech.catalog_authentication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product product) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> searchByName(String name) {
        return productRepository.searchFilterByName(name);
    }
}
