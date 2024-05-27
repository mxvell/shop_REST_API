package prachykAndMoroka.market.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prachykAndMoroka.market.dto.ProductFromJsonDTO;
import prachykAndMoroka.market.manager.BasketProductManager;
import prachykAndMoroka.market.manager.JsonValidationResult;
import prachykAndMoroka.market.model.Basket;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.model.User;
import prachykAndMoroka.market.repository.ProductRepository;
import prachykAndMoroka.market.repository.UserRepository;
import prachykAndMoroka.market.utill.JsonParsingException;
import prachykAndMoroka.market.utill.ProductNotFoundException;
import prachykAndMoroka.market.utill.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final BasketProductManager basketProductManager;
    private final ProductRepository productRepository;

    @Autowired
    public UserService(UserRepository userRepository, BasketProductManager basketProductManager, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.basketProductManager = basketProductManager;
        this.productRepository = productRepository;
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


    public List<ProductFromJsonDTO> getAllProductsInBasket(long userId) throws UserNotFoundException, JsonParsingException {
        User user = findById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        Basket basket = user.getBasket();
        if (basket == null) {
            throw new NullPointerException("USER BASKET IS NULL");
        }

        String basketData = basket.getBasketData();
        if (basketData == null || basketData.length() == 0) {
            JsonValidationResult result = basketProductManager.isValidJson(basketData);
            if (result.isValid()) {
                System.out.println("JSON IS VALID");
            } else {
                System.out.println("JSON IS INVALID" + result.getErrorMsg());
            }

        }

        List<ProductFromJsonDTO> result;
        try {
            result = basketProductManager.getAllProducts(basketData);
        } catch (JsonProcessingException e) {
            return (List<ProductFromJsonDTO>) new JsonParsingException("Error parsing basket data for user ", e);
        }

        return result;
    }

    @Transactional
    public void deleteProductByIndexInBasket(long userId, long productId, int quantity) throws UserNotFoundException {
        User user = findById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        Basket basket = user.getBasket();
        if (basket == null) {
            throw new NullPointerException("USER BASKET IS NULL");
        }
        String basketData = basket.getBasketData();
        if (basketData == null || basketData.length() == 0) {
            JsonValidationResult result = basketProductManager.isValidJson(basketData);
            if (result.isValid()) {
                System.out.println("JSON IS VALID");
            } else {
                System.out.println("JSON IS INVALID" + result.getErrorMsg());
            }
        }

        String updatedJson;
        try {
            List<ProductFromJsonDTO> updatedList = basketProductManager.deleteProductFromBasket(productId, quantity, basketData);
            updatedJson = basketProductManager.generateJson(updatedList);
        } catch (JsonProcessingException e) {
            throw new JsonParsingException("Error parsing basket data", e);
        }
        basket.setBasketData(updatedJson);
        user.setBasket(basket);
        userRepository.save(user);
    }


    @Transactional
    public void addProductToBasket(long userId, long productId, int quantity) throws UserNotFoundException, JsonParsingException, JsonProcessingException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("No product  found with id " + productId));
        basketProductManager.addProductToBasket(user, product, quantity);

    }
   @Transactional
    public void deleteAllProductsInBasket(long userId) throws UserNotFoundException, JsonProcessingException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        basketProductManager.deleteAllProductToBasket(user);
    }
}
