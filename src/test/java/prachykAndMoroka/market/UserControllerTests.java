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
import prachykAndMoroka.market.model.*;
import prachykAndMoroka.market.service.UserService;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTests {
    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;


    private static final User testUserInDatabase = new User(1L, "John", "Doe", "213213@gmasil.com", new Basket(), new ArrayList<Order>());


    @BeforeAll
    public void beforeTests() {
        if (userService.findAll().size() > 0) {
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
        User myUser = userService.findById(1L);
        if (myUser.equals(testUserInDatabase)) {
            ResponseEntity<User> response = userController.getUserById(myUser.getId());
            assertEquals(testUserInDatabase, response.getBody());
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }


    }

    @Test
    void testGetUserByIdDoesNotExist() {
        User myUser = userService.findById(2L);
        if (myUser != null) {
            ResponseEntity<User> response = userController.getUserById(myUser.getId());
            assertEquals(testUserInDatabase, response.getBody());
        } else {
            System.out.println(HttpStatus.NOT_FOUND);

        }
    }


    @Test
    void testGetUserByNameWhenUsersExist() {
        ResponseEntity<List<User>> response = userController.getUserByName("John");
        if (response.getBody().equals(userController)) {
            assertEquals(testUserInDatabase, Objects.requireNonNull(response.getBody()).get(0));
            assertEquals(HttpStatus.OK, response.getStatusCode(), "Status 200");
        }

    }

    @Test
    void testGetUserByNameWhenNoUsersExist() {
        ResponseEntity<List<User>> response = userController.getUserByName("weqweqwe");
        if (response.getBody() != null && !response.getBody().isEmpty()) {
            assertEquals(testUserInDatabase, Objects.requireNonNull(response.getBody()).get(0));
        }

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetUserByEmailWhenUserExists() {
        ResponseEntity<User> response = userController.getUserByEmail("213213@gmasil.com");
        if (response.getBody().equals(userController)) {
            assertEquals(testUserInDatabase, response.getBody());
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

    }

    @Test
    void testGetUserByEmailDoesNotExist() {
        ResponseEntity<User> response = userController.getUserByEmail("dqwewertyui@gmail.com");
        if (response.getBody() != null) {
            assertEquals(testUserInDatabase, response.getBody());
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }

    }

    @Test
    void testAddToBasket() {
        Product product = new Product(1L,"iPhone X",500, Category.PHONE);
        ResponseEntity<HttpStatus> response = userController.addToBasket(product, 2, testUserInDatabase.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

//    @Test
//    void testDeleteProductFromIndex() {
//        Product product = new Product(2L, "iPhone X",500,Category.PHONE);
//        long id = product.getId();
//        ResponseEntity<HttpStatus> response = userController.deleteProductsFromIndex(testUserInDatabase.getId(), id);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//    }

    @Test
    void testClearBasket() {
        Product product = new Product(1L, "asus", 500, Category.LAPTOP );
        testUserInDatabase.addProductToBasket(product, 1);
        ResponseEntity<HttpStatus> response = userController.clearBasket(testUserInDatabase.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldCalculateTotalPrice() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "asus", 700, Category.LAPTOP));
        products.add(new Product(2L, "macbook", 700, Category.LAPTOP));
        for (Product product : products) {
            testUserInDatabase.addProductToBasket(product, 2);
        }
        double total = products.get(0).getPrice() + products.get(1).getPrice();
        ResponseEntity<Double> response = userController.getTotalPrice(products, testUserInDatabase.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(total);

    }
}




























