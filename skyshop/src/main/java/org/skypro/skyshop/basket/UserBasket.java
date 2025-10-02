package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.List;

public class UserBasket {
   private List<BasketItem> basketItems;
   private int total;

    public UserBasket(List<BasketItem> basketItems){
        this.basketItems = basketItems;
        this.total = basketItems.stream().
                mapToInt(s -> s.getItem() * s.getProduct().getPrice()).sum();
    }

    public List<BasketItem> getBasketItems(){
        return basketItems;
    }

    public int getTotal(){
        return total;
    }


}
