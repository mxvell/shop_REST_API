package prachykAndMoroka.market.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prachykAndMoroka.market.dto.UserDTO;
import prachykAndMoroka.market.model.Basket;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.model.User;
import prachykAndMoroka.market.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;

    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable String name) {
        List<User> users = userService.findByName(name);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserDTO userDTO) {
        userService.saveUser(convertToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    public User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/basket/add")
    public ResponseEntity<HttpStatus> addToBasket(@RequestBody Product products, @RequestParam int quantity, @RequestParam long id) {
        User user = userService.findById(id);
        user.addProductToBasket(products, quantity);
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @DeleteMapping("/basket/delete{id}")
    public ResponseEntity<HttpStatus> deleteProductsFromIndex(@PathVariable long id) {
        User user = userService.findById(id);
        user.deleteProductFromBasketIndex(id);
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/basket/deleteAll")
    public ResponseEntity<HttpStatus> clearBasket(@RequestParam long id) {
        User user = userService.findById(id);
        user.deleteAllProductsFromBasket();
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/basket/total{id}")
    public ResponseEntity<Double> getTotalPrice(@RequestParam List<Product> products, @PathVariable long id) {
        User user = userService.findById(id);
        user.getTotalPriceInBasket(products);
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }
}

























