package org.skypro.skyshop.product;

import java.util.UUID;

public class FixPriceProduct extends org.skypro.skyshop.product.Product {
    private final int fixPrice = 78;
    private final UUID id;

    public FixPriceProduct(UUID id, String name){
        super(id, name);
        this.id = id;
    }
    public UUID getUUID(){return id;}

    @Override
    public int getPrice(){
        return fixPrice;

    }

    @Override
    public String toString(){
        return "Продукт с фиксированной ценой: " + this.getNameProduct() + " фиксированная цена: " + this.fixPrice + " руб." + "\n";
    }

    @Override
    public boolean isSpecial(){
        return true;
    }

}
