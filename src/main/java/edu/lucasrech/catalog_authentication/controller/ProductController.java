package edu.lucasrech.catalog_authentication.controller;

import edu.lucasrech.catalog_authentication.exception.ValueExpectedException;
import edu.lucasrech.catalog_authentication.model.product.Product;
import edu.lucasrech.catalog_authentication.model.product.ProductDTO;
import edu.lucasrech.catalog_authentication.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Catálogo de produtos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Obtém uma lista com todos os produtos cadastrados no catálogo", method = "GET")
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @Operation(summary = "Obtém um produto específico baseado em seu ID único. Requisição do tipo path", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @Operation(summary = "Obtém uma lista de produtos com base em uma categoria selecionada.", method = "GET")
    @GetMapping(value = "/category/{category}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(productService.getProductsByCategory(category));
    }

    @Operation(summary = "Realiza a busca de uma lista de produtos que contenham o nome inserido.", method = "GET")
    @GetMapping(value = "/name", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductByName(@RequestParam String name) {
        return ResponseEntity.ok().body(productService.searchByName(name));
    }

    @Operation(summary = "Cadastra um novo produto no banco de dados.", method = "POST")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO product) throws ValueExpectedException {
        return ResponseEntity.ok().body(productService.createProduct(product));
    }

    @Operation(summary = "Atualiza um produto, informando o id do produto a ser atualizado e os novos dados", method = "PUT")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@RequestBody String id, Product product) {
        return ResponseEntity.ok().body(productService.updateProduct(id, product));
    }

    @Operation(summary = "Realiza a exclusão de um produto com base em seu id.", method = "DELETE")
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProduct(@RequestBody String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}
