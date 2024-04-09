package prachykAndMoroka.market.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prachykAndMoroka.market.dto.ProductFromJsonDTO;
import prachykAndMoroka.market.model.Product;
import prachykAndMoroka.market.service.ProductService;

import java.sql.PreparedStatement;
import java.util.ArrayList;
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


    public List<ProductFromJsonDTO> deleteProductFromBasket(long productId, int quantity, String basketData) throws JsonProcessingException {
        List<ProductFromJsonDTO> productList = getAllProducts(basketData);
        for (ProductFromJsonDTO product : productList){
              if (product.getProductId() == productId){
                  int actualQty = product.getQuantity();
                  int newQty = actualQty - quantity;
                  if (newQty <= 0){
                      productList.remove(product);
                  }else {
                      product.setQuantity(newQty);
                  }
                  break;
              }
        }
        return productList;
    }

    public String generateJson(List<ProductFromJsonDTO> productList) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(productList);
    }

}
