package org.skypro.skyshop.service;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.Searchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class StorageService {
    private final Map<UUID, Product> product = new HashMap<>();
    private final Map<UUID, Article> article = new HashMap<>();

    public StorageService() {initialize(); }

    public void initialize(){
        addProduct(new SimpleProduct(UUID.randomUUID(), "milk", 94));
        addProduct(new SimpleProduct(UUID.randomUUID(), "coffe", 225));
        addProduct(new DiscountedProduct(UUID.randomUUID(), "cucumbers", 78, 10));
        addProduct(new DiscountedProduct(UUID.randomUUID(), "помидоры", 115, 5));
        addProduct(new FixPriceProduct(UUID.randomUUID(), "хлеб"));
        addProduct(new FixPriceProduct(UUID.randomUUID(), "яйцо"));

        addArticle(new Article(UUID.randomUUID(), "10 Новых рецептов блинчиков", "Приготовление блинчиков с начинкой"));
    }

    public Collection<Product> getProduct() {
        return product.values();
    }
    public Collection<Article> getArticle(){
        return article.values();

    }
    public Collection<Searchable> getSearchable(){
        return Stream.concat(product.values().stream(), article.values().stream()).collect(Collectors.toList());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(product.get(id));
    }

    public void addProduct(Product products){
        if (products != null){
            product.put(products.getId(), products);
        }
    }

    public void addArticle(Article articles){
        if (articles != null){
            article.put(articles.getId(), articles);
        }
    }


}
