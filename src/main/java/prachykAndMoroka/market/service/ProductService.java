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

    public Product findById(int id) {
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
    public void saveProduct(ProductDTO productSaved, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;

            Product product = new Product();
            if (file1.getSize() != 0) {
                image1 = toImageEntity(file1);
                product.addImageToProduct(image1);
            }
            if (file2.getSize() != 0) {
                image2 = toImageEntity(file1);
                product.addImageToProduct(image2);
            }
            if (file3.getSize() != 0) {
                image3 = toImageEntity(file1);
                product.addImageToProduct(image3);
            }
            product.setId(productSaved.getProduct().getId());
            product.setName(productSaved.getName());
            product.setCategory(productSaved.getProduct().getCategory());
            product.setPrice(productSaved.getPrice());
            Product productFromDb = productRepository.save(product);
            productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
            productRepository.save(product);
        }


        private Image toImageEntity (MultipartFile file) throws IOException {
            Image image = new Image();
            image.setName(file.getName());
            image.setOriginalFileName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            image.setBytes(file.getBytes());
            return image;
        }


        @Transactional
        public void updateProduct ( int id, Product updProduct){

            updProduct.setId(id);
            productRepository.save(updProduct);
        }

        @Transactional
        public void deleteProduct ( int id){
            productRepository.deleteById(id);
        }
        public void deleteAll () {
            productRepository.deleteAll();
        }

    }

