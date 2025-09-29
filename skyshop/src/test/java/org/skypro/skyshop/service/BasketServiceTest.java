package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private ProductBasket productBasket = new ProductBasket();
    private StorageService storageService;
    private BasketService basketService;

    @Test
    public void addingAProductToTheCart(){
        Mockito.when(storageService.getProductById(UUID.randomUUID())).thenReturn(Optional.ofNullable(null));
        Exception thrownException = null;
        try {
            basketService.productById(UUID.randomUUID());
        } catch (Exception e) {
            thrownException = e;
        }

        assertThat(thrownException)
                .isNotNull()
                .isExactlyInstanceOf(NoSuchProductException.class);

    }
}
