package prachykAndMoroka.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prachykAndMoroka.market.model.CategoryEntity;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired

    public List<Product> findAll (){
        return productRepository.findAll();
    }
    public Product findById(int id){
        Optional<Product> foundProduct = productRepository.findById(id);
       return foundProduct.orElse(null);
    }

    public List<Product> findByCategories (CategoryEntity category){
        return productRepository.findByCategories(category);
    }
    public Product findByNameStartingWith(String firstWordsProducts){
        return productRepository.findByNameStartingWith(firstWordsProducts);
    }
    @Transactional
    public void saveProduct (Product productSaved){
        productRepository.save(productSaved);
    }
    @Transactional
    public void updateProduct(int id, Product updProduct){
        updProduct.setId(id);
        productRepository.save(updProduct);
    }
    @Transactional
    public void delete(int id){
        productRepository.deleteById(id);
    }

}
