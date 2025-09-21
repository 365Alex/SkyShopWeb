package org.skypro.skyshop.controller;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.exception.ShopError;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> noSuchProductException(NoSuchProductException s){
        ShopError shopError = new ShopError("NO_SUCH_PRODUCT_WITH_THIS_ID", "Продукт с таким id не найден");
        return new ResponseEntity<>(shopError, HttpStatusCode.valueOf(404));
    }

}
