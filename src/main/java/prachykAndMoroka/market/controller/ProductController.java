package prachykAndMoroka.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import prachykAndMoroka.market.dto.ProductDTO;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.service.ProductService;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    public ResponseEntity<HttpStatus> createProduct(@RequestBody ProductDTO productDTO,@RequestParam("file1")MultipartFile file1,
                                                    @RequestParam("file2")MultipartFile file2,@RequestParam("file3")MultipartFile file3)  throws IOException {
        productService.saveProduct(productDTO,file1,file2,file3);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateProduct(@PathVariable int id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/random")
    public ResponseEntity<Product> getRandomProduct(){
        List<Product> productList = productService.findAll();
        if (productList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        int randomIndex = new Random().nextInt(productList.size());
        Product randomProduct = productList.get(randomIndex);
        return ResponseEntity.ok(randomProduct);
    }

    @GetMapping("/random/{count}")
    public ResponseEntity<List<Product>> getSomeRandomProducts(@PathVariable int count){
        List<Product> allProducts = productService.findAll();
        if (allProducts.size() < count){
            return ResponseEntity.badRequest().build();
        }
        Collections.shuffle(allProducts);
        List<Product> randomProducts = new ArrayList<>();
        for (int i = 0; i < count; i++){
            randomProducts.add(allProducts.get(i));
        }
        return ResponseEntity.ok(randomProducts);
    }
}
