package org.skypro.skyshop.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int percent;
    private final UUID id;

    public DiscountedProduct(UUID id,String name, int basPrice, int percent){
        super(id, name);
        this.basePrice = basPrice;
        this.percent = percent;
        this.id = id;
        if (basePrice <= 0){
            throw new IllegalArgumentException("цена не должна быть меньше или равна 0");
        }
        if (percent < 0 || percent > 100){
            throw new IllegalArgumentException("процент должен быть от 0 до 100");
        }

    }

    public UUID getId(){return id;}

    @Override
    public int getPrice(){

        return basePrice - (basePrice * percent /100);
    }


    @Override
    public String toString(){
        return "Продукт: " + this.getNameProduct() +  " стоимость без скидки: " + this.basePrice + " руб." +
                " скидка: " + this.percent + "%" + " цена со скидкой: " + this.getPrice() + " руб." + "\n";
    }


    @Override
    public boolean isSpecial(){
        return true;
    }



}
