package prachykAndMoroka.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import prachykAndMoroka.market.model.CategoryEntity;
import prachykAndMoroka.market.model.Product;


import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
     List<Product> findByCategories(CategoryEntity category);
     Product findByNameStartingWith(String firstWordsProducts);
}
