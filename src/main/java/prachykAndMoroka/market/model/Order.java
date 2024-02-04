package prachykAndMoroka.market.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user_id;
//    @ManyToOne
//    private Product product_id;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    public Order() {
    }

    public Order(int id, User user_id, OrderStatus orderStatus) {
        this.id = id;
        this.user_id = user_id;
        this.orderStatus = OrderStatus.IN_PROGRESS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Order(User user_id) {
        this.user_id = user_id;
    }


    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

}
