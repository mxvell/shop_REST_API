package prachykAndMoroka.market;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import prachykAndMoroka.market.controller.UserController;
import prachykAndMoroka.market.model.User;
import prachykAndMoroka.market.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class UserControllerTests {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetUserByIdWhenUserExists() {
        int id = 1;
        User user = new User(id, "Dima");
        Mockito.when(userService.findById(anyInt())).thenReturn(user);
        ResponseEntity<User> response = userController.getUserById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetUserByIdDoesNotExist() {
        int notCorrectId = 2;
        Mockito.when(userService.findById(anyInt())).thenReturn(null);
        ResponseEntity<User> response = userController.getUserById(notCorrectId);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
    }


    @Test
    void testGetUserByNameWhenUsersExist() {
        String name = "Dima";
        List<User> mockUsers = Arrays.asList(new User(1, name), new User(2, name));
        Mockito.when(userService.findByName(anyString())).thenReturn(mockUsers);

        ResponseEntity<List<User>> responseEntity = userController.getUserByName(name);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockUsers, responseEntity.getBody());
    }

    @Test
    void testGetUserByNameWhenNoUsersExist() {
        String name = "doesn't exist name";
        Mockito.when(userService.findByName(anyString())).thenReturn(Arrays.asList());
        ResponseEntity<List<User>> response = userController.getUserByName(name);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() == null || response.getBody().isEmpty());
    }
    @Test
    void testGetUserByEmailWhenUserExists(){
        String emailUser = "1234@gmail.com";
        User user = new User(1,"Dima");
        Mockito.when(userService.findByEmail(anyString())).thenReturn(user);
        ResponseEntity<User> response = userController.getUserByEmail(emailUser);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(user,response.getBody());
    }
   @Test
    void testGetUserByEmailDoesNotExist(){
        String emailUser = "not found";
        Mockito.when(userService.findByEmail(anyString())).thenReturn(null);
        ResponseEntity<User> response = userController.getUserByEmail(emailUser);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
   }

}




























