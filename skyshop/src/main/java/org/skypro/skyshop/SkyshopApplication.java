package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
public class SkyshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkyshopApplication.class, args);

		ProductBasket productBasket = new ProductBasket();
		DiscountedProduct cheese = new DiscountedProduct(UUID.randomUUID(), "сыр", 150, 20);
		FixPriceProduct productTwo = new FixPriceProduct(UUID.randomUUID(), "конфеты");
		SimpleProduct productTree = new SimpleProduct(UUID.randomUUID(), "молоко", 95);
		DiscountedProduct productFor = new DiscountedProduct(UUID.randomUUID(), "колбаса", 200, 15);
		SimpleProduct productFife = new SimpleProduct(UUID.randomUUID(), "макароны", 84);

		Map<Product, Product> basketOne = new HashMap<>();
		basketOne.put(cheese, productTwo);
		basketOne.put(productTree, productFor);
		System.out.println(basketOne);


		productBasket.addProduct(cheese);
		productBasket.addProduct(productFife);
		productBasket.addProduct(productTree);
		productBasket.addProduct(productFor);
		productBasket.addProduct(productTwo);
		productBasket.printProductBasket();
	}

}
