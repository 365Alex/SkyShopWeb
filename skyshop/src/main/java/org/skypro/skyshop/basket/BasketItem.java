package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class BasketItem {
    private Product product;
    private int item;

    public BasketItem(Product product, int item){
        this.product =product;
        this.item = item;
    }

    public Product getProduct(){
        return  product;
    }

    public int getItem(){
        return item;
    }

}
