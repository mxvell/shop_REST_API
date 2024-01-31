package prachykAndMoroka.market.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "product", schema = "public")
@Component
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @Column(name = "name")
    @JsonProperty("name")
    private String name;
    private double price;
    @JsonProperty("category")
    @Enumerated(EnumType.STRING)
    private Category category;
    //    @OneToMany(mappedBy = "product_id")
//    private List<Order> orders;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "product")

    private List<Image> images = new ArrayList<>();
    private Long previewImageId;

    public Product() {
    }
    public void addImageToProduct(Image image) {
        image.setProduct(this);
        images.add(image);
    }

    public Product(String name, Category categories) {
        this.name = name;
        this.category = categories;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Long getPreviewImageId() {
        return previewImageId;
    }

    public void setPreviewImageId(Long previewImageId) {
        this.previewImageId = previewImageId;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


