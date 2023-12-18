package prachykAndMoroka.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import prachykAndMoroka.market.model.Category;
import prachykAndMoroka.market.model.CategoryEntity;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.service.ProductService;

import java.util.List;

//@RestController
//@RequestMapping("/product")
//public class ProductController {
//    private final ProductService productService;
//
//    @Autowired
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productService.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProduct(@PathVariable int id) {
//        Product product = productService.findById(id);
//        return product != null ?
//                ResponseEntity.ok(product) :
//                ResponseEntity.notFound().build();
//    }
//
//    @PostMapping
//    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
//        productService.saveProduct(product);
//        return ResponseEntity.ok(product);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
//        productService.updateProduct(id, product);
//        return ResponseEntity.ok(product);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
//        productService.delete(id);
//        return ResponseEntity.ok().build();
//    }
//}
