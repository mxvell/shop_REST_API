package prachykAndMoroka.market.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "basket", schema = "public")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "basket")
    private User user;

    @JsonRawValue
    @Column(columnDefinition = "TEXT")
    private String basketData;


    public Basket() {
    }


    public void addItem(Product product, int quantity) {
        List<BasketItem> items = getItems();
        items.add(new BasketItem(product.getId(), quantity));
        setItems(items);

    }

    public List<BasketItem> getItems() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(basketData, objectMapper.getTypeFactory().constructCollectionType(List.class, BasketItem.class));
        } catch (JsonProcessingException e) {
            return new ArrayList<>();
        }
    }

    public void setItems(List<BasketItem> items) {
        try {
            basketData = new ObjectMapper().writeValueAsString(items);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing basket data", e);
        }
    }

    public List<Product> getProducts() {
        //TODO: implement in controller and delete
        return null;
    }

    public void deleteProductsByIndex(Long index) {
        //TODO: implement in controller and delete
    }

    public void deleteAllProducts() {
       //TODO: implement in controller and delete
    }

    public double getTotalPrice(List<Product> products) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }


    public void setProducts(List<Product> products) {
        //TODO: implement in controller and delete
    }


    public Basket(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBasketData() {
        return basketData;
    }

    public void setBasketData(String basketData) {
        this.basketData = basketData;
    }
}
