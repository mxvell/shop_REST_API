package prachykAndMoroka.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import prachykAndMoroka.market.dto.ProductDTO;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.service.ImageService;
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
    private final ImageService imageService;

    @Autowired
    public ProductController(ProductService productService, ImageService imageService) {
        this.productService = productService;
        this.imageService = imageService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
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
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     *Щоб застосувати цей ендпоінт /products/{productId}/images за допомогою Postman для завантаження зображень, виконайте наступні кроки:
     *
     *     Відкрийте Postman та створіть новий запит
     *     Виберіть метод HTTP як POST
     *     Введіть URL-адресу ендпоінту Наприклад, якщо ваш додаток працює на http://localhost:8080 та потрібно завантажити зображення для продукту з id=1, URL-адреса буде: http://localhost:8080/products/1/images
     *     Перейдіть до вкладки "Body"
     *     Виберіть тип тіла запиту як "form-data"
     *     Там де пише "Key" напиши file
     *     В параметрі "Value" вибери фото продукту
     * @param productId
     * @param file
     * @return
     */
    @PostMapping("/{productId}/images")
    public ResponseEntity<String> uploadImagesForProduct(@PathVariable Long productId, @RequestParam("file") MultipartFile file) {
        try {
            imageService.uploadImagesForProduct(productId, file);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to upload image");
        }

    }

    @PostMapping
    public ResponseEntity<HttpStatus> createProduct(@RequestBody ProductDTO product) throws IOException {
        productService.saveProduct(product);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateProduct(@PathVariable long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<Product> getRandomProduct() {
        List<Product> productList = productService.findAll();
        if (productList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        int randomIndex = new Random().nextInt(productList.size());
        Product randomProduct = productList.get(randomIndex);
        return ResponseEntity.ok(randomProduct);
    }

    @GetMapping("/random/{count}")
    public ResponseEntity<List<Product>> getMultiplyRandomProducts(@PathVariable int count) {
        List<Product> allProducts = productService.findAll();
        if (allProducts.size() < count) {
            return ResponseEntity.badRequest().build();
        }
        Collections.shuffle(allProducts);
        List<Product> randomProducts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            randomProducts.add(allProducts.get(i));
        }
        return ResponseEntity.ok(randomProducts);
    }
}
