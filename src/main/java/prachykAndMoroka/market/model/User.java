package prachykAndMoroka.market.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

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
//    @JsonIgnore
//    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
//    private List<Order> orders;

    public User() {

    }

    public User(String name, String surname, String email, Basket basket) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.basket = basket;

    }

    public User(Long id, String name, String surname, String email, Basket basket) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.basket = basket;

    }
    // todo ПЕРЕНЕСТИ МЕТОД get total price in basket  з класу юзер до userService
    public double getTotalPriceInBasket(List<Product> products) {
        return basket.getTotalPrice(products);
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
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
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(basket, user.basket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, basket);
    }
}
