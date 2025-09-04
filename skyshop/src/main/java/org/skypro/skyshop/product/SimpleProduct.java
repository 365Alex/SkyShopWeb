package org.skypro.skyshop.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;
    private final UUID id;

    public SimpleProduct(UUID id, String name, int price){
        super(id, name);
        this.price = price;
        this.id = id;
        if (price <= 0){
            throw new IllegalArgumentException("неверная цена продукта");
        }
    }

    public UUID getUUID(){return id;}

    @Override
    public int getPrice(){
        return price;
    }

    @Override
    public String toString(){
        return "Продукт: " + this.getNameProduct() + " цена: " + this.price + " руб." + "\n";
    }

    @Override
    public boolean isSpecial(){
        return false;
    }


}
