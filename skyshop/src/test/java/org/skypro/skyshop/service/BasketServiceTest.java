package org.skypro.skyshop.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchResult;

import java.util.*;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private ProductBasket productBasket = new ProductBasket();
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;

    @Test
    public void addingAProductToTheCart(){
        when(storageService.getProductById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () ->
        {basketService.productById(UUID.randomUUID());});

    }

    /**
     * "чтобы добавить товар в корзину
     * я пытаюсь сделать так:
     * productBasket.addProduct(добавленный продукт SimpleProduct)
     * Но для productBasket нет метода addProduct (только для storageService)
     * как добавить продукт в корзину?
     * Сначала же нужно добавить продукт в корзину, чтобы потом сделать тест
     */
    @Test
    public void addingAnExistingProduct(){
        when(storageService.getProduct()).thenReturn(List.of(
                new SimpleProduct(UUID.randomUUID(), "tea", 57)));
        List<SearchResult> results = storageService.addProduct("tea");
        assertFalse(results.isEmpty());

    }

    /**
     * .вернуть пустую корзину, если ProductBasket пуст
     */
    @Test
    public void returnEmptyCartIfProductBasketIsEmpty(){
        when(productBasket.getBasket()).thenReturn(Optional.empty());
        List<ProductBasket> result = basketService.getUseBasket();
        assertFalse(result.isEmpty());

    }

}
