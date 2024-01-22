package prachykAndMoroka.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import prachykAndMoroka.market.model.Basket;
import prachykAndMoroka.market.model.Product;

import java.util.List;
@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {

    void addProduct(List<Product> products, int quantity);
    void deleteByProducts(List<Product> products);
    double totalPriceInBasket(List<Product> products, int quantity);
}
