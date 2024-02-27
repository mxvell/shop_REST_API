package prachykAndMoroka.market.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;
import prachykAndMoroka.market.model.Basket;

public class UserDTO {
    @NotEmpty(message = "not empty")
    @Size(min = 3, max = 30, message = "between 3 and 30")
    private String name;
    @NotEmpty(message = "not empty")
    @Size(min = 3, max = 30, message = "between 3 and 30")
    private String surname;
    @Email
    @NotEmpty
    @UniqueElements
    private String email;
    @NotEmpty
    private Basket basket;

    public UserDTO(String name, String surname, String email, Basket basket) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.basket = basket;
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

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
