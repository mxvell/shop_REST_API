package prachykAndMoroka.market.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prachykAndMoroka.market.dto.ProductFromJsonDTO;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.model.User;
import prachykAndMoroka.market.service.ProductService;
import prachykAndMoroka.market.utill.EmptyBasketException;
import prachykAndMoroka.market.utill.JsonParsingException;

import java.util.List;

@Component
public class BasketProductManager {

    private final ProductService productService;

    @Autowired
    public BasketProductManager(ProductService productService) {
        this.productService = productService;
    }

    public List<ProductFromJsonDTO> getAllProducts(String jsonStr) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<ProductFromJsonDTO> jsonProductsList = mapper.readValue(jsonStr,
                mapper.getTypeFactory().constructCollectionType(List.class, ProductFromJsonDTO.class)
        );

        return jsonProductsList;
    }

    /**
     * Extracts {@link Product} instance from database by if of {@link ProductFromJsonDTO} instance.
     *
     * @return instance of {@link Product} from database or null in case instance with this id doesn't exist in database
     */
    private Product extractProduct(ProductFromJsonDTO productFromJsonDTO) {
        return productService.findById(productFromJsonDTO.getProductId());
    }


    public List<ProductFromJsonDTO> deleteProductFromBasket(long productId, int quantity, String basketData) throws JsonProcessingException, EmptyBasketException {
        List<ProductFromJsonDTO> productList = getAllProducts(basketData);
        for (ProductFromJsonDTO product : productList) {
            if (product.getProductId() == productId) {
                int actualQty = product.getQuantity();
                int newQty = actualQty - quantity;
                if (newQty <= 0) {
                    productList.remove(product);
                } else {
                    product.setQuantity(newQty);
                    throw new EmptyBasketException("You can't delete product, because basket empty");
                }
                break;
            }
        }
        return productList;
    }


    public String generateJson(List<ProductFromJsonDTO> productList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(productList);
    }


    public JsonValidationResult isValidJson(final String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.readTree(json);
            return new JsonValidationResult(true, null);
        } catch (JsonProcessingException e) {
            return new JsonValidationResult(false, e.getMessage());
        }
    }

    public void addProductToBasket(User user, Product product, int quantity) throws JsonProcessingException {
        if (user.getBasket() == null || user.getBasket().getBasketData() == null || user.getBasket().getBasketData().isEmpty()) {
            throw new JsonParsingException("User basket is null tech error");
        }

        List<ProductFromJsonDTO> productsFromBasketJson = getAllProducts(user.getBasket().getBasketData());
        boolean isFoundInBasket = false;
        for (ProductFromJsonDTO pr : productsFromBasketJson) {
            if (pr.getProductId() == product.getId()) {
                pr.setQuantity(pr.getQuantity() + quantity);
                isFoundInBasket = true;
                break;
            }
        }
        if (isFoundInBasket) {
            productsFromBasketJson.add(new ProductFromJsonDTO(product.getId(), quantity));
        }
        user.getBasket().setBasketData(generateJson(productsFromBasketJson));
    }

    public void deleteAllProductToBasket(User user) throws JsonProcessingException {
        if (user.getBasket() == null || user.getBasket().getBasketData() == null || user.getBasket().getBasketData().isEmpty()) {
            throw new JsonParsingException("User basket is null tech error");
        }
        List<ProductFromJsonDTO> productsFromBasketJson = getAllProducts(user.getBasket().getBasketData());
          if (!productsFromBasketJson.isEmpty()){
              productsFromBasketJson.clear();
              user.getBasket().setBasketData(generateJson(productsFromBasketJson));
        }else {
              throw new EmptyBasketException("You can't delete all products, because basket empty");
          }
    }
}
