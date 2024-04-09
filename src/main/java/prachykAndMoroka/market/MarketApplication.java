package prachykAndMoroka.market;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import prachykAndMoroka.market.dto.BasketDTO;
import prachykAndMoroka.market.model.Basket;
import prachykAndMoroka.market.model.Product;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;


@SpringBootApplication
@ComponentScan(basePackages = "prachykAndMoroka.market.repository")
@ComponentScan(basePackages = "prachykAndMoroka.market.service")
public class MarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
