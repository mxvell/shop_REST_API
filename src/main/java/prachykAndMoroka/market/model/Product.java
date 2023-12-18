package prachykAndMoroka.market.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table(name = "product",schema = "public")
@Component
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "product")

    private List<CategoryEntity> categories;

    @OneToMany(mappedBy = "product_id")
    private List<Order> orders;

    public Product() {
    }

    public Product(String name, List<CategoryEntity> categories, List<Order> orders) {
        this.name = name;
        this.categories = categories;
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                ", orders=" + orders +
                '}';
    }
}
