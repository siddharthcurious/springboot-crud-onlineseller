package com.shop.products.onlineseller;

import com.shop.products.onlineseller.models.Product;
import com.shop.products.onlineseller.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlinesellerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinesellerApplication.class, args);
	}

	private static final Logger logger = LoggerFactory.getLogger(OnlinesellerApplication.class);

	@Bean
	public CommandLineRunner setup(ProductRepository productRepository) {
		return (args) -> {
			productRepository.save(new Product("TV", "Electronics", 4500.0));
			productRepository.save(new Product("Pant ", "Cloth", 9000.0));
			productRepository.save(new Product("Table", "Accessories", 4677.0));
			productRepository.save(new Product("Computer", "Electronics", 45500.0));
			productRepository.save(new Product("Shirt ", "Cloth", 3400.0));
			productRepository.save(new Product("Chair", "Accessories", 4677.0));
			productRepository.save(new Product("Mobile", "Electronics", 4500.0));
			productRepository.save(new Product("T-shirt ", "Cloth", 9000.0));
			productRepository.save(new Product("Jeans", "Cloth", 4677.0));
			logger.info("The sample data has been generated");
		};
	}

}
