package prachykAndMoroka.market;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.NoRepositoryBean;
import prachykAndMoroka.market.dto.ProductDTO;
import prachykAndMoroka.market.model.Category;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.service.ProductService;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTests {
    @Autowired
    private ProductService productService;

//    public static final ProductDTO testProductDTODataBase = new ProductDTO("ASUS ROG gl-12", );
//    public static final Product testProductDataBase = new Product(1,"ASUS ROG gl-12", Category.LAPTOP);
//    @BeforeAll
//    public void beforeTests(){
//         if (productService.findAll().size() > 0){
//             productService.deleteAll();
//         }
//         productService.saveProduct(testProductDTODataBase);
//    }
//    @AfterAll
//    public void afterTests(){
//        productService.deleteAll();
//    }
//    @Test
//    void testFindByIdWhenProductExists(){
//        Product myProduct = productService.findById(1);
//        assertEquals(testProductDataBase,myProduct);
//    }
}
