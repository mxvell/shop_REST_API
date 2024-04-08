package prachykAndMoroka.market.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prachykAndMoroka.market.dto.ProductFromJsonDTO;
import prachykAndMoroka.market.manager.BasketProductManager;
import prachykAndMoroka.market.model.Basket;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.model.User;
import prachykAndMoroka.market.repository.ProductRepository;
import prachykAndMoroka.market.repository.UserRepository;
import prachykAndMoroka.market.utill.UserNotFoundException;

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
    public UserService(UserRepository userRepository, BasketProductManager basketProductManager, ProductService productService) {
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
    public List<ProductFromJsonDTO> getAllProductsInBasket(long userId) throws UserNotFoundException  {
        User user = findById(userId);
            if (user == null){
              throw new UserNotFoundException(userId);
            }

        Basket basket = user.getBasket();
        if (basket == null) {
            return null;
        }

        String basketData = basket.getBasketData();
        //TODO add JSON validation
        if (basketData == null || basketData.length() == 0) {
            return null;
        }

        List<ProductFromJsonDTO> result;
        try {
            result = basketProductManager.getAllProducts(basketData);
        } catch (JsonProcessingException e) {
            //TODO переделать
            return null;
        }

        return result;
    }

    @Transactional
    public void deleteProductByIndexInBasket(long userId, long productId, int quantity) {
        User user = findById(userId);
        if (user == null) {
            throw new RuntimeException("USER IS NULL");
        }
        Basket basket = user.getBasket();
        if (basket == null) {
            throw new RuntimeException("USER BASKET IS NULL");
        }
        String basketData = basket.getBasketData();
        if (basketData == null || basketData.length() == 0) {
            throw new RuntimeException("USER BASKET DATA IS NULL OR EMPTY");
        }

        String updatedJson;
        try {
            List<ProductFromJsonDTO> updatedList = basketProductManager.deleteProductFromBasket(productId, quantity, basketData);
            updatedJson = basketProductManager.generateJson(updatedList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Paring error JSON");
        }
        basket.setBasketData(updatedJson);
        user.setBasket(basket);
        userRepository.save(user);
    }
}
