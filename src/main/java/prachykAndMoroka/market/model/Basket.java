package prachykAndMoroka.market.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "basket", schema = "public")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "basket")
    private User user;

    @JsonRawValue
    @Column(columnDefinition = "TEXT")
    private String basketData;


    public Basket() {
    }

    public List<Product> getProducts() {
        //TODO: implement in service and delete
        return null;
    }

    public void deleteProductsByIndex(Long index) {
        //TODO: implement in service  and delete
    }

    public void deleteAllProducts() {
        //TODO: implement in service and delete
    }

    public double getTotalPrice(List<Product> products) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }


    public void setProducts(List<Product> products) {
        //TODO: implement in service and delete
    }


    public Basket(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBasketData() {
        return basketData;
    }

    public void setBasketData(String basketData) {
        this.basketData = basketData;
    }
}
