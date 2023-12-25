package prachykAndMoroka.market.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")

    private String name;
    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;


    @OneToOne(mappedBy = "user")
    private Basket basket;
    @OneToMany(mappedBy = "user_id")
    private List<Order>orders;

    public User() {

    }

    public User(String name, String surname, String email, Basket basket, List<Order> orders) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.basket = basket;
        this.orders = orders;
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
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", basket=" + basket +
                ", orders=" + orders +
                '}';
    }
}
