package org.skypro.skyshop.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private String name;
    private UUID id;

    public Product(UUID id, String name){
        this.id = id;
    this.name = name;
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("некорректное название продукта");
        }

    }

    public String getNameProduct(){
        return this.name;
    }

    public abstract int getPrice();

    public UUID getId(){return id;}

    public abstract String toString();

    @JsonIgnore
    @Override
    public String searchTerm(){
        return getName();
    }

    @JsonIgnore
    @Override
    public String getContent(){
        return "PRODUCT";
    }

    @Override
    public String getName() {
        return name;

    }

    public String isBlank(){
        return name;
    }

    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
       Product product = (Product) obj;
        return name.equals(product.name);
    }
    public int hashCode(){
        return Objects.hash(name);
    }

    public abstract boolean isSpecial();
}
