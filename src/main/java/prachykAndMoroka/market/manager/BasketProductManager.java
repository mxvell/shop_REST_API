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

    public List<Product> getAllProducts(String jsonStr) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<ProductFromJsonDTO> jsonProductsList = mapper.readValue(jsonStr,
                mapper.getTypeFactory().constructCollectionType(List.class, ProductFromJsonDTO.class)
        );

        return extractMultipleProducts(jsonProductsList);
    }

    @Nullable
    private List<Product> extractMultipleProducts(List<ProductFromJsonDTO> inputList) {
        if (inputList == null) {
            return null;
        }

        List<Product> resultList = new ArrayList<>();
        for (ProductFromJsonDTO jsonDtoProduct : inputList) {
            resultList.add(extractProduct(jsonDtoProduct));
        }

        return resultList;
    }

    /**
     * Extracts {@link Product} instance from database by if of {@link ProductFromJsonDTO} instance.
     *
     * @return instance of {@link Product} from database or null in case instance with this id doesn't exist in database
     */
    private Product extractProduct(ProductFromJsonDTO productFromJsonDTO) {
        return productService.findById(productFromJsonDTO.getProductId());
    }

//    public boolean isValidJSON(final String json) {
//        boolean valid = false;
//        try {
//            final JsonParser parser = new ObjectMapper().getJsonFactory()
//                    .createJsonParser(json);
//            while (parser.nextToken() != null) {
//            }
//            valid = true;
//        } catch (JsonParseException jpe) {
//            jpe.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//
//        return valid;
//    }

    public void deleteProductWithId(long id){
       Product product = productService.findById(id);
       if (product == null){
           System.out.println("Not found product");
       }else {
           productService.deleteProduct(product.getId());
       }
    }
}
