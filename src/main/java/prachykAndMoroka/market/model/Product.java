package prachykAndMoroka.market.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import org.springframework.core.serializer.Deserializer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Customizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "product", schema = "public")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName("product_id")
    private Long id;
    @Column(name = "name")
    @JsonProperty("name")
    private String name;
    private double price;
    @JsonProperty("category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Image> images = new ArrayList<>();


    public Product() {
    }

    public Product(String name, Category categories) {
        this.name = name;
        this.category = categories;
    }

    public Product(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product(Long id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Product(Long id, String name, double price, Category laptop) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = laptop;
    }


    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0 && name.equals(product.name) && category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category);
    }
}


