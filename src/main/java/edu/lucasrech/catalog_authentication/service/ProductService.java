package edu.lucasrech.catalog_authentication.service;

import edu.lucasrech.catalog_authentication.exception.ValueExpectedException;
import edu.lucasrech.catalog_authentication.model.Product;
import edu.lucasrech.catalog_authentication.dto.ProductDTO;
import edu.lucasrech.catalog_authentication.model.enums.Category;
import edu.lucasrech.catalog_authentication.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product createProduct(ProductDTO product) throws ValueExpectedException {
        Product newProduct = new Product(product);
        if(product.description().isEmpty() || product.name().isEmpty() || product.price() == 0) {
            final String[] values = new String[3];
            values[0] = product.name();
            values[1] = product.description();
            values[2] = Double.toString(product.price());
            throw new ValueExpectedException(values);

        } else {
            productRepository.save(newProduct);
        }
        return newProduct;
    }

    public Product updateProduct(String id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setImageUrl(product.getImageUrl());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);

    }

    public List<Product> getProductsByCategory(String category) {
        Category categoryEnum = Category.valueOf(category.toUpperCase());
        return productRepository.findByCategory(categoryEnum);
    }

    public List<Product> searchByName(String name) {
        return productRepository.searchFilterByName(name);
    }
}
