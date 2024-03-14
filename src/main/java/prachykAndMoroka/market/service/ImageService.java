package prachykAndMoroka.market.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import prachykAndMoroka.market.model.Image;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.repository.ImageRepository;
import prachykAndMoroka.market.repository.ProductRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {
     private final ImageRepository imageRepository;
     private final ProductRepository productRepository;
     @Autowired

     public ImageService(ImageRepository imageRepository, ProductRepository productRepository) {
          this.imageRepository = imageRepository;
          this.productRepository = productRepository;
     }

     public void uploadImagesForProduct(Long productId, MultipartFile file) throws IOException {
          Product product = productRepository.findById(productId).orElseThrow(()-> new EntityNotFoundException("Product not found"));
          Image image = new Image();
          image.setProduct(product);
          image.setData(file.getBytes());
          imageRepository.save(image);
     }
}
