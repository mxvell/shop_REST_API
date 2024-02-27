package prachykAndMoroka.market.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "basket", schema = "public")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private User user;
    @OneToMany
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

    public void deleteProductsByIndex(int index) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
