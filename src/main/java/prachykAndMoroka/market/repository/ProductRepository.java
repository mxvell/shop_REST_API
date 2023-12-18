package prachykAndMoroka.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prachykAndMoroka.market.model.CategoryEntity;
import prachykAndMoroka.market.model.Product;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
     List<Product> findByCategories(CategoryEntity category);
     Product findByNameStartingWith(String firstWordsProducts);
}
