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


    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    CategoryEntity categories;

//    @OneToMany(mappedBy = "product_id")
//    private List<Order> orders;

    public Product() {
    }

    public Product(String name, CategoryEntity categories) {
        this.name = name;
        this.categories = categories;
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

    public CategoryEntity getCategories() {
        return categories;
    }

    public void setCategories(CategoryEntity categories) {
        this.categories = categories;
    }

}



