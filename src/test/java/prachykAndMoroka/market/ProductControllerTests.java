package prachykAndMoroka.market;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import prachykAndMoroka.market.controller.ProductController;
import prachykAndMoroka.market.dto.ProductDTO;
import prachykAndMoroka.market.model.Category;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.service.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllerTests {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductController productController;

    public static final ProductDTO testProductDTODataBase = new ProductDTO("ASUS ROG gl-12", Category.LAPTOP);
    public static final Product testProductDataBase = new Product(1L, "ASUS ROG gl-12", Category.LAPTOP);

    @BeforeAll
    public void beforeTests() throws IOException {
        testProductDTODataBase.setProduct(testProductDataBase);
        testProductDTODataBase.getProduct().setId(1L);
        if (productService.findAll().size() > 0) {
            productService.deleteAll();
        }
        productService.saveProduct(testProductDTODataBase);
    }

    @AfterAll
    public void afterTests() {
        productService.deleteAll();
    }

    @Test
    void testGetProductByIdWhenExists() {
        Product product = productService.findById(1);
        ResponseEntity<Product> response = productController.getProductById(product.getId());
        assertEquals(testProductDataBase, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetProductByIdWhenDoesNotExist() {
        Product notExistingProduct = productService.findById(2);
        if (notExistingProduct != null) {
            ResponseEntity<Product> response = productController.getProductById(notExistingProduct.getId());
            assertNotEquals(testProductDataBase, response.getBody());
        } else {
            System.out.println(HttpStatus.NOT_FOUND);
        }
    }

    @Test
    void testGetRandomProduct() {
        ResponseEntity<Product> response = productController.getRandomProduct();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        long id = response.getBody().getId();
        assertTrue(id == 1 || id == 2);
    }


}

