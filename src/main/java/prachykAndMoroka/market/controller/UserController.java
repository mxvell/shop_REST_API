package prachykAndMoroka.market.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prachykAndMoroka.market.dto.UserDTO;
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
    public ResponseEntity<User> getUserById(@PathVariable int id) {
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
    public User convertToUser(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }


    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable int id, @RequestBody User user) {
        userService.updateUser(id, user);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

























