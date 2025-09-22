package org.skypro.skyshop.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> basket = new HashMap<>();

    public void mapBasket(UUID productId){
        basket.put(productId, basket.getOrDefault(productId, 0)+1);
    }

    public Map<UUID, Integer> getBasket(){
        return Collections.unmodifiableMap(basket);
    }

}
