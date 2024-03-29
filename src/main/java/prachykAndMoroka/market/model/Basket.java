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
//    @SerializedName("id")
    private Long id;

    @OneToOne(mappedBy = "basket")
    private User user;
    // TODO DELETE
    @OneToMany(mappedBy = "productInBasket", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products;
    @JsonRawValue
    @Column(columnDefinition = "TEXT")
    private String basketData;


//    @Column(name = "quantity")
//    private int quantity;

    public Basket() {
    }


//    public Basket(List<Product> products, int quantity) {
//        this.products = products;
//        this.quantity = quantity;
//    }

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
        if (products == null) {
            products = new LinkedList<>();
        }
        return products;
    }

    public void deleteProductsByIndex(Long index) {
        if (products.size() > index) {
            products.remove(index);
        }
    }

    public void deleteAllProducts() {
        if (!products.isEmpty()) {
            products.clear();
        }
    }

    public double getTotalPrice(List<Product> products) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }


    public void setProducts(List<Product> products) {
        this.products = products;
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
