package org.skypro.skyshop.controller;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ShopController {
    private final StorageService storageService;

    public ShopController(StorageService storageService){
        this.storageService = storageService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getProduct().values();

    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles(){
        return storageService.getArticle().values();
    }
}
