package prachykAndMoroka.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prachykAndMoroka.market.model.Image;
import prachykAndMoroka.market.repository.ImageRepository;

import java.util.Optional;

@Service
public class ImageService {
     private final ImageRepository imageRepository;
     @Autowired
     public ImageService(ImageRepository imageRepository) {
          this.imageRepository = imageRepository;
     }

     public Image findById (Long id){
          Optional<Image> image = imageRepository.findById(id);
          return image.orElse(null);
     }
}
