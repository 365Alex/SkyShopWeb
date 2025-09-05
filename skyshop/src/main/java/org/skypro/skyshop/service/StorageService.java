package org.skypro.skyshop.service;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.search.Searchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class StorageService {
    private final Map<UUID, Product> product;
    private final Map<UUID, Article> article;

    public StorageService() {
        this.product = new HashMap<>();
        this.article = new HashMap<>();
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


}
