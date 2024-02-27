package prachykAndMoroka.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prachykAndMoroka.market.model.Image;
@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}
