package prachykAndMoroka.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prachykAndMoroka.market.model.Order;
import prachykAndMoroka.market.model.OrderStatus;
import prachykAndMoroka.market.repository.OrderRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findById(long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    @Transactional
    public void updateOrderStatus(long orderId, OrderStatus orderStatus) {
        Order order = findById(orderId);
        if (order == null) {
            throw new NullPointerException("Order not found with id " + orderId);
        }
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

}
