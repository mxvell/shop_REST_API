package prachykAndMoroka.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prachykAndMoroka.market.dto.ProductDTO;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("by-name/{name}")
    public ResponseEntity<List<Product>> getByFirstWordsProducts(@PathVariable String startWith) {
        List<Product> products = productService.findByNameStartingWith(startWith);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createProduct(@RequestBody ProductDTO productDTO) {
        productService.saveProduct(productDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateProduct(@PathVariable int id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
