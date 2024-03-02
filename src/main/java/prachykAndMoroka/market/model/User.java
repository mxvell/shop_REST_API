package prachykAndMoroka.market.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private Basket basket;
    @OneToMany(mappedBy = "user_id", fetch = FetchType.EAGER)
    private List<Order> orders;

    public User() {

    }

    public User(String name, String surname, String email, Basket basket, List<Order> orders) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.basket = basket;
        this.orders = orders;
    }

    public User(Long id, String name, String surname, String email, Basket basket, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.basket = basket;
        this.orders = orders;
    }

    public void addProductToBasket(Product product, int quantity) {
        for (int i = 1; i < quantity; i++) {
            basket.getProducts().add(product);

        }
    }

    public void deleteProductFromBasketIndex(long index) {
        if (!basket.getProducts().isEmpty()) {
            basket.deleteProductsByIndex(index);
        }
    }

    public void deleteAllProductsFromBasket() {
        basket.deleteAllProducts();
    }

    public double getTotalPriceInBasket(List<Product> products) {
        return basket.getTotalPrice(products);
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(basket, user.basket) && Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, basket, orders);
    }
}
