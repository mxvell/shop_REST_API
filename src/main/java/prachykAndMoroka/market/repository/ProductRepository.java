package prachykAndMoroka.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prachykAndMoroka.market.model.Category;
import prachykAndMoroka.market.model.Product;
import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
     List<Product> findByCategory(Category category);
     List<Product> findByNameStartingWith(String firstWordsProducts);
}
