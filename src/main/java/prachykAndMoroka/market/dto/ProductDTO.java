package prachykAndMoroka.market.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;
import prachykAndMoroka.market.model.Product;


public class ProductDTO {
    @JsonProperty("name")
    @NotEmpty(message = "not empty")
    @UniqueElements
    private String name;
    @JsonProperty("product")
    private Product product;

    public ProductDTO(String name, Product product) {
        this.name = name;
        this.product = product;
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
}
