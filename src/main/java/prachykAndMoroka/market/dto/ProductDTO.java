package prachykAndMoroka.market.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;
import prachykAndMoroka.market.model.Basket;
import prachykAndMoroka.market.model.Category;
import prachykAndMoroka.market.model.Image;
import prachykAndMoroka.market.model.Product;


public class ProductDTO {

    @JsonProperty("name")
    @NotEmpty(message = "not empty name of product")
    @UniqueElements
    private String name;
    @JsonProperty("product")
    @UniqueElements
    private Product product;
    @NotNull
    @NotEmpty
    @Min(15)
    @Max(40000)
    private double price;
    private Image image;
    private Category category;

    public ProductDTO() {
    }

    public ProductDTO(String name, Product product, double price, Image image) {
        this.name = name;
        this.product = product;
        this.price = price;
        this.image = image;

    }

    public ProductDTO(String name, Product product, double price) {
        this.name = name;
        this.product = product;
        this.price = price;
    }

    public ProductDTO(String name, Category laptop) {
        this.name = name;
        this.category = laptop;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", product=" + product +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}

