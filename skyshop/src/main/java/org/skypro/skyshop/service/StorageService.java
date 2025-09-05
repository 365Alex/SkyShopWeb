package org.skypro.skyshop.service;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Service

public class StorageService {
    private final Map<UUID, Product> product;
    private final Map<UUID, Article> article;

    public StorageService(Map<UUID, Product> product, Map<UUID, Article> article) {
        this.product = new HashMap<>();
        this.article = new HashMap<>();
    }

    public Map<UUID, Product> getProduct() {
        return product;
    }
    public Map<UUID, Article> getArticle(){
        return article;

    }


}
