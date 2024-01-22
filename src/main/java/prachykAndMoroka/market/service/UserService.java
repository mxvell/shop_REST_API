package prachykAndMoroka.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.model.User;
import prachykAndMoroka.market.repository.UserRepository;

import java.net.PortUnreachableException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final BasketService basketService;
    @Autowired
    public UserService(UserRepository userRepository, BasketService basketService) {
        this.userRepository = userRepository;
        this.basketService = basketService;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById (int id){
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }
    public List<User> findByName(String name){
        return userRepository.findByName(name);
    }
    public User findByEmail (String email){
        return userRepository.findByEmail(email);
    }

    public void saveUser (User userSaved){
        userRepository.save(userSaved);
    }

    public void updateUser(int id,User updUser){
        updUser.setId(id);
        userRepository.save(updUser);
    }

    public void delete(int id){
        userRepository.deleteById(id);
    }
    
    public void deleteAll() {
        userRepository.deleteAll();
    }
    @Transactional
    public void addToBasket(List<Product> products, int quantity){
        basketService.addProductInBasket(products,quantity);
    }
    @Transactional
    public void deleteToBasket(List<Product> products){
        basketService.deleteProductInBasket(products);
    }
    @Transactional
    public double getTotalPrice(List<Product> products, int quant){
        return basketService.getTotalPriceInBasket(products,quant);
    }
}
