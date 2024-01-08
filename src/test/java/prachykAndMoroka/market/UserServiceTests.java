package prachykAndMoroka.market;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import prachykAndMoroka.market.model.Order;
import prachykAndMoroka.market.model.User;
import prachykAndMoroka.market.service.UserService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTests {

    @Autowired
    private UserService userService;

    private static final User testUserInDatabase = new User(1, "John", "Doe", "213213@gmasil.com", null, new ArrayList<Order>());

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
        User myUser = userService.findById(1);

        assertEquals(testUserInDatabase, myUser);
    }

//    @Test
//    void testFindByIdWhenUserDoesNotExist() {
////        int userId = 2;
////        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
////        User result = userService.findById(userId);
//        assertNull(result);
//    }
//
//    @Test
//    void testFindByNameWhenUsersExist() {
////        String name = "John Doe";
////        List<User> mockUsers = Arrays.asList(new User(1, name), new User(2, name));
////        Mockito.when(userRepository.findByName(anyString())).thenReturn(mockUsers);
////
////
////        List<User> result = userService.findByName(name);
//
//
//        assertEquals(mockUsers, result);
//    }
//
//    @Test
//    void testFindByNameWhenNoUsersExist() {
//
////        String name = "Nonexistent Name";
////        Mockito.when(userRepository.findByName(anyString())).thenReturn(Arrays.asList());
////
////        List<User> result = userService.findByName(name);
//
//        assertEquals(0, result.size());
//    }
//
//    @Test
//    void testFindByEmailWhenUserExists() {
////        String email = "weqeqw@gmail.com";
////        User user = new User(1, "Rock");
////        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
////        User result = userService.findByEmail(email);
//        assertEquals(testUserInDatabase, result);
//    }
//
//    @Test
//    void testFindByEmailWhenNoUserExists() {
//
////        String nonExistentEmail = "nonexistent@gmail.com";
////        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(null);
////
////        User result = userService.findByEmail(nonExistentEmail);
//
//        assertNull(result);
//    }
//
//    @Test
//    void testSaveUser() {
////        User user = new User(1, "John", "Doe", "213213@gmasil.com");
////        userService.saveUser(user);
////        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
//    }
}

