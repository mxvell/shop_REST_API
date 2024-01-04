package prachykAndMoroka.market;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import prachykAndMoroka.market.model.User;
import prachykAndMoroka.market.repository.UserRepository;
import prachykAndMoroka.market.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testFindByIdWhenUserExists() {

        int userId = 1;
        User user = new User(userId, "John", "Doe", "213213@gmasil.com");
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        User result = userService.findById(userId);


        assertEquals(user, result);
    }

    @Test
    void testFindByIdWhenUserDoesNotExist() {
        int userId = 2;
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        User result = userService.findById(userId);
        assertNull(result);
    }

    @Test
    void testFindByNameWhenUsersExist() {
        String name = "John Doe";
        List<User> mockUsers = Arrays.asList(new User(1, name), new User(2, name));
        Mockito.when(userRepository.findByName(anyString())).thenReturn(mockUsers);


        List<User> result = userService.findByName(name);


        assertEquals(mockUsers, result);
    }

    @Test
    void testFindByNameWhenNoUsersExist() {

        String name = "Nonexistent Name";
        Mockito.when(userRepository.findByName(anyString())).thenReturn(Arrays.asList());

        List<User> result = userService.findByName(name);

        assertEquals(0, result.size());
    }

    @Test
    void testFindByEmailWhenUserExists() {
        String email = "weqeqw@gmail.com";
        User user = new User(1, "Rock");
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        User result = userService.findByEmail(email);
        assertEquals(user, result);
    }

    @Test
    void testFindByEmailWhenNoUserExists() {

        String nonExistentEmail = "nonexistent@gmail.com";
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(null);

        User result = userService.findByEmail(nonExistentEmail);

        assertNull(result);
    }

    @Test
    void testSaveUser() {
        User user = new User(1, "John", "Doe", "213213@gmasil.com");
        userService.saveUser(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }
}

