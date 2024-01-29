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
    public Order() {
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
