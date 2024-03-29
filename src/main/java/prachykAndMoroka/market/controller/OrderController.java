package prachykAndMoroka.market.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prachykAndMoroka.market.model.Order;
import prachykAndMoroka.market.model.OrderStatus;
import prachykAndMoroka.market.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {

        try {
            Order order = orderService.findById(id);
            return ResponseEntity.ok(order);
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Boolean> updateStatus(@PathVariable Long id, @RequestParam OrderStatus orderStatus) {
        try {
            orderService.updateOrderStatus(id, orderStatus);
            return ResponseEntity.ok(true);
        } catch (NullPointerException exception) {
            return ResponseEntity.badRequest().body(false);
        }

    }
}
