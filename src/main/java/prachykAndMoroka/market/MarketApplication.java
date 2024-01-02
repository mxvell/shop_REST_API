package prachykAndMoroka.market;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.catalina.util.SessionConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import prachykAndMoroka.market.dto.ProductDTO;
import prachykAndMoroka.market.model.Category;

@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(MarketApplication.class, args);

//		ObjectMapper mapper = new ObjectMapper();
//		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        ProductDTO productDTO = new ProductDTO("Iphone xs", new CategoryEntity("PHONE"));
//		String json = mapper.writeValueAsString(productDTO);
//		ProductDTO deserialized = mapper.readValue(json,ProductDTO.class);
//		CategoryEntity category = new CategoryEntity();
//		System.out.println(category.getCategory("Laptop"));
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
