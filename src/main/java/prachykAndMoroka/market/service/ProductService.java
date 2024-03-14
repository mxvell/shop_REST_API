package prachykAndMoroka.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import prachykAndMoroka.market.dto.ProductDTO;
import prachykAndMoroka.market.model.Category;
import prachykAndMoroka.market.model.Image;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.repository.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(long id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        return foundProduct.orElse(null);
    }

    public List<Product> findByCategories(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> findByNameStartingWith(String firstWordsProducts) {
        List<Product> result = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (product.getName().startsWith(firstWordsProducts)) ;
            result.add(product);
        }
        return productRepository.findByNameStartingWith(firstWordsProducts);
    }


    @Transactional
    public void saveProduct(ProductDTO productSaved) throws IOException {
        Product product = new Product();
        product.setId(productSaved.getProduct().getId());
        product.setName(productSaved.getName());
        product.setCategory(productSaved.getProduct().getCategory());
        product.setPrice(productSaved.getPrice());
        product.setProductInBasket(productSaved.getProduct().getProductInBasket());
        product.setOrders(productSaved.getProduct().getOrders());
        productRepository.save(product);
    }


    /**
     *This method upd product's data
     * @param id
     * @param updProduct
     */
    @Transactional
    public void updateProduct(long id, Product updProduct) {
        updProduct.setId(id);
        productRepository.save(updProduct);
    }

    @Transactional
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

}

