package org.skypro.skyshop.service;

import org.skypro.skyshop.basket.BasketItem;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.basket.UserBasket;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService){
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void productById(UUID productId){
        Product product = storageService.getProductById(productId).orElseThrow(
                NoSuchProductException::new);
        productBasket.mapBasket(product.getId());
    }

    public UserBasket getUseBasket(){
        List<BasketItem> basketItems =  productBasket.getBasket().entrySet().stream().
                map(s -> new BasketItem(storageService.getProductById(s.getKey()).
                        orElseThrow(NoSuchProductException::new), s.getValue())).toList();
        return new UserBasket(basketItems);
    }
}
