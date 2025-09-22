package org.skypro.skyshop.controller;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.UserBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchResult;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketService basketService;

    @Autowired
    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService){
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getProduct();
    }
    @PatchMapping("/products")
    public void addProduct(@RequestBody SimpleProduct product){
        storageService.addProduct(product);
    }

     @GetMapping("/articles")
    public Collection<Article> getAllArticles(){
        return storageService.getArticle();
    }
    @PatchMapping("/articles")
    public void addArticle(@RequestBody Article article){
        storageService.addArticle(article);
    }

    @GetMapping("/search")
    public Collection<SearchResult> findPattern(@RequestParam("pattern")String pattern){
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("{id}") UUID id){
        basketService.productById(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/basket/")
    public UserBasket getUserBasket(){
        return basketService.getUseBasket();
    }







}
