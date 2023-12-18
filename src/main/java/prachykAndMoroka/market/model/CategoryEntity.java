package prachykAndMoroka.market.model;

import jakarta.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Product product;

    public CategoryEntity() {
    }

    public CategoryEntity(Category category, Product product) {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", category=" + category +
                ", product=" + product +
                '}';
    }
}
