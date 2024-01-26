package prachykAndMoroka.market;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import prachykAndMoroka.market.controller.UserController;
import prachykAndMoroka.market.model.Order;
import prachykAndMoroka.market.model.User;
import prachykAndMoroka.market.service.UserService;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.refEq;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTests {
    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    private static final User testUserInDatabase = new User(1, "John", "Doe", "213213@gmasil.com", null, new ArrayList<Order>());
    @BeforeAll
    public void beforeTests(){
        if (userService.findAll().size() > 0){
            userService.deleteAll();
        }

        userService.saveUser(testUserInDatabase);
    }


    @AfterAll
    public void afterTest() {
        userService.deleteAll();
    }


    @Test
    void testGetUserByIdWhenUserExists() {
        User myUser = userService.findById(1);
        ResponseEntity<User> response = userController.getUserById(myUser.getId());
        assertEquals(testUserInDatabase,response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    void testGetUserByIdDoesNotExist() {
        User myUser = userService.findById(2);
        if (myUser != null) {
            ResponseEntity<User> response = userController.getUserById(myUser.getId());
            assertEquals(testUserInDatabase, response.getBody());
        } else {
            System.out.println(HttpStatus.NOT_FOUND);

        }
    }


    @Test
    void testGetUserByNameWhenUsersExist() {
//        List<User> myUser = userService.findByName("John");
        ResponseEntity<List<User>> response = userController.getUserByName("John");
        assertEquals(testUserInDatabase, Objects.requireNonNull(response.getBody()).get(0));
        assertEquals(HttpStatus.OK,response.getStatusCode(),"Status 200");
    }

    @Test
    void testGetUserByNameWhenNoUsersExist() {
        ResponseEntity<List<User>> response = userController.getUserByName("weqweqwe");
        if (response.getBody() != null && !response.getBody().isEmpty()){
            assertEquals(testUserInDatabase, Objects.requireNonNull(response.getBody()).get(0));
        }

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }
    @Test
    void testGetUserByEmailWhenUserExists(){
        ResponseEntity<User> response = userController.getUserByEmail("213213@gmasil.com");
        assertEquals(testUserInDatabase, response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
   @Test
    void testGetUserByEmailDoesNotExist(){
    ResponseEntity<User> response = userController.getUserByEmail("dqwewertyui@gmail.com");
    if (response.getBody() != null){
        assertEquals(testUserInDatabase,response.getBody());
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

   }

}




























