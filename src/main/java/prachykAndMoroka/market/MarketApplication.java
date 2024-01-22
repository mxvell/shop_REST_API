package prachykAndMoroka.market;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "prachykAndMoroka.market.repository")
@ComponentScan(basePackages = "prachykAndMoroka.market.service")
public class MarketApplication {

	public static void main(String[] args)  {
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
