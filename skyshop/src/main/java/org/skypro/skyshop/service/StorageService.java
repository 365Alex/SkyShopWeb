package org.skypro.skyshop.service;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service

public class StorageService {
    private final Map<UUID, Product> product;
    private final Map<UUID, Article> article;
    private Collection<Product> storageService;

    public StorageService(Map<UUID, Product> product, Map<UUID, Article> article) {
        this.product = product;
        this.article = article;
    }

    public Map<UUID, Product> getProduct() {
        return product;
    }
    public Map<UUID, Article> getArticle(){
        return article;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getAllProducts();
    }
    @GetMapping("/articles")
    public Collection<Article> getAllArticles(){
        return storageService.getAllArticles();
    }


}
