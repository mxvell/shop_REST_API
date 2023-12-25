package prachykAndMoroka.market.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category",schema = "public")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "category_name")
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToMany(mappedBy = "categories")
    private List<Product> product;

    public CategoryEntity() {
    }


    public CategoryEntity(Category category, List<Product> product) {
        this.category = category;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
