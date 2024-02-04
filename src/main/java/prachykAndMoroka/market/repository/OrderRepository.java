package prachykAndMoroka.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prachykAndMoroka.market.model.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
