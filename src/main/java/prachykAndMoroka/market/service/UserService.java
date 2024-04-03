package prachykAndMoroka.market.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prachykAndMoroka.market.manager.BasketProductManager;
import prachykAndMoroka.market.model.Basket;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.model.User;
import prachykAndMoroka.market.repository.ProductRepository;
import prachykAndMoroka.market.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final ProductService productService;
    private final BasketProductManager basketProductManager;

    @Autowired
    public UserService(UserRepository userRepository, BasketProductManager basketProductManager,ProductService productService) {
        this.userRepository = userRepository;
        this.basketProductManager = basketProductManager;
        this.productService = productService;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void saveUser(User userSaved) {
        userRepository.save(userSaved);
    }

    @Transactional
    public void updateUser(Long id, User updUser) {
        updUser.setId(id);
        userRepository.save(updUser);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    //TODO: replace null with exception throw
    public List<Product> getAllProductsInBasket(long userId) {
        User user = findById(userId);
        if (user == null) {
            return null;
        }

        Basket basket = user.getBasket();
        if (basket == null) {
            return null;
        }

        String basketData = basket.getBasketData();
        //TODO add JSON validation
        if (basket == null || basketData.length() == 0) {
            return null;
        }

        List<Product> result;
        try {
            result = basketProductManager.getAllProducts(basketData);
        } catch (JsonProcessingException e) {
            //TODO переделать
            return null;
        }

        return result;
    }
    @Transactional
    public void deleteProductByIndexInBasket(long userId,long productId) {
        User user = findById(userId);
        if (user == null) {
            return;
        }

        Basket basket = user.getBasket();
        if (basket == null) {
            return;
        }

        String basketData = basket.getBasketData();
        //TODO add JSON validation
        if (basket == null || basketData.length() == 0) {
            return;
        }
        basketProductManager.deleteProductWithId(productId);
    }
}
