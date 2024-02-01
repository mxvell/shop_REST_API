package prachykAndMoroka.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prachykAndMoroka.market.model.Image;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
