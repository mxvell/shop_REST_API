package prachykAndMoroka.market.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;
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
    public ProductDTO(String name, Product product, double price) {
        this.name = name;
        this.product = product;
        this.price = price;
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
}
