package prachykAndMoroka.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prachykAndMoroka.market.model.Basket;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.repository.BasketRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BasketService {
    private final BasketRepository basketRepository;


    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }



    @Transactional
    public void addProductInBasket(List<Product> products, int quantity) {
        basketRepository.addProduct(products, quantity);
    }

    @Transactional
    public void deleteProductInBasket(List<Product> products) {
        basketRepository.deleteByProducts(products);
    }

   @Transactional
    public double getTotalPriceInBasket(List<Product> products, int quant){
        Basket basket = new Basket();
         basketRepository.totalPriceInBasket(products,quant);
         double total = 0;
         for (Product product : products){
             double price = product.getPrice();
             int quantity = basket.getQuantity();
             total += price * quantity;
         }
         return total;
   }
}
