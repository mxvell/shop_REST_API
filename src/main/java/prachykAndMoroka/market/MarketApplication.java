package prachykAndMoroka.market;

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
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
