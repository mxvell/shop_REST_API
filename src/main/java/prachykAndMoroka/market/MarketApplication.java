package prachykAndMoroka.market;

import org.apache.catalina.util.SessionConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import prachykAndMoroka.market.model.Category;
import prachykAndMoroka.market.model.CategoryEntity;

@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);

//		CategoryEntity category = new CategoryEntity();
//		System.out.println(category.getCategory("Laptop"));
	}

}
