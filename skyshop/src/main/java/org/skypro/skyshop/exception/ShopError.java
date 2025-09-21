package org.skypro.skyshop.exception;

public class ShopError<S> {
    private final String code;
    private final String message;

    public ShopError(String code, String message){
        this.code = code;
        this.message = message;
    }

}
