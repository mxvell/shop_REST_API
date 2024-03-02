package prachykAndMoroka.market.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "basket", schema = "public")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "basket")
    private User user;
    @OneToMany(mappedBy = "productInBasket",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Product> products;
    private int quantity;

    public Basket() {
    }

    public Basket(List<Product> products, int quantity) {
        this.products = products;
        this.quantity = quantity;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void deleteProductsByIndex(long index) {
        if (products.size() > index) {
            products.remove(index);
        }
    }

    public void deleteAllProducts() {
        if (!products.isEmpty()) {
            products.clear();
        }
    }

    public double getTotalPrice(List<Product> products) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Basket)) return false;
        Basket basket = (Basket) o;
        return id == basket.id && quantity == basket.quantity && user.equals(basket.user) && products.equals(basket.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, products, quantity);
    }
}
