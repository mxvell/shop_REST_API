package prachykAndMoroka.market.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User userId;
    @ManyToOne(cascade = CascadeType.ALL)
    private Product productId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    public Order() {
    }

    public Order(Long id, User userId, OrderStatus orderStatus) {
        this.id = id;
        this.userId = userId;
        this.orderStatus = OrderStatus.IN_PROGRESS;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Order(User userId) {
        this.userId = userId;
    }


    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }
}
