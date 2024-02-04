package prachykAndMoroka.market.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prachykAndMoroka.market.model.Order;
import prachykAndMoroka.market.model.OrderStatus;
import prachykAndMoroka.market.service.OrderService;

@RestController("/order")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable int id, @RequestParam OrderStatus orderStatus){
        try {
            orderService.updateOrderStatus(id,orderStatus);
            return ResponseEntity.ok().build();
        }catch (NullPointerException exception){
            return ResponseEntity.notFound().build();
        }

    }
}
