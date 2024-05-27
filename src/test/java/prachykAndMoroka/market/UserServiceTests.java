package prachykAndMoroka.market;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prachykAndMoroka.market.model.Basket;
import prachykAndMoroka.market.model.Order;
import prachykAndMoroka.market.model.User;
import prachykAndMoroka.market.service.UserService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTests {

    @Autowired
    private UserService userService;

    private static final User testUserInDatabase = new User(1L, "John", "Doe", "213213@gmasil.com", new Basket(), new Order());

    @BeforeAll
    public void beforeTests() {
        
        if (userService.findAll().size() > 0){
            userService.deleteAll();
        }
        
        userService.saveUser(testUserInDatabase);
    }
    
    @AfterAll
    public void afterTests () {
        userService.deleteAll();
    }

    @Test
    void testFindByIdWhenUserExists() {
        User myUser = userService.findById(1L);

        assertEquals(testUserInDatabase, myUser);
    }

    @Test
    void testFindByIdWhenUserDoesNotExist() {
      User myUser = userService.findById(2L);
      assertNotEquals(testUserInDatabase,myUser,"This user doesn't exist with this id");
    }

    @Test
    void testFindByNameWhenUsersExist() {
      List<User> myUser = userService.findByName("John");
      assertEquals(testUserInDatabase,myUser.get(0));

    }

    @Test
    void testFindByNameWhenNoUsersExist() {


        List<User> myUser = userService.findByName("AQAQAQ");

        assertNotEquals(testUserInDatabase,myUser, "That user didn't find by name because it doesn't have one");
    }

    @Test
    void testFindByEmailWhenUserExists() {
      User emailUser = userService.findByEmail("213213@gmasil.com");
        assertEquals(testUserInDatabase,  emailUser);
    }

    @Test
    void testFindByEmailWhenNoUserExists() {
        User emailUser = userService.findByEmail("trterte.@gmail.com");
        assertNotEquals(testUserInDatabase,emailUser, "Doesn't exist email");

    }

}

