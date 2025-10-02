package org.skypro.skyshop.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.basket.BasketItem;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.basket.UserBasket;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    private BasketService basketService;

    private UUID existingProductId;
    private UUID nonExistingProductId;
    private Product existingProduct;

    @BeforeEach
    void setUp() {
        basketService = new BasketService(productBasket, storageService);

        existingProductId = UUID.randomUUID();
        nonExistingProductId = UUID.randomUUID();
        existingProduct = new SimpleProduct(existingProductId, "Test Product", 100);
    }

    /**
     * Добавление несуществующего товара в корзину
     * приводит к выбросу исключения
     */
    @Test
    void productById_WhenProductDoesNotExist_ThrowsNoSuchProductException() {
        /**
         * подготовка
         */
        when(storageService.getProductById(nonExistingProductId))
                .thenReturn(Optional.empty());

        /**
         * действие
         */

        assertThrows(NoSuchProductException.class,
                () -> basketService.productById(nonExistingProductId));
        /**
         * проверка
         */

        verify(productBasket, never()).mapBasket(any(UUID.class));
    }

    /**
     * Добавление существующего товара вызывает метод addProduct
     * у мока ProductBasket
     */
    @Test
    void productById_WhenProductExists_CallsMapBasketOnProductBasket() {

        when(storageService.getProductById(existingProductId))
                .thenReturn(Optional.of(existingProduct));


        basketService.productById(existingProductId);

        verify(productBasket, times(1)).mapBasket(existingProductId);
    }

    /**
     * Метод getUserBasket возвращает пустую корзину, если ProductBasket пуст
     */

    @Test
    void getUserBasket_WhenProductBasketIsEmpty_ReturnsEmptyUserBasket() {

        when(productBasket.getBasket()).thenReturn(Collections.emptyMap());


        UserBasket result = basketService.getUseBasket();


        assertNotNull(result);
        assertTrue(result.getBasketItems().isEmpty());
        assertEquals(0, result.getTotal());
    }

    /**
     * Метод getUserBasket возвращает корзину, если в ProductBasket есть товары
     */

    @Test
    void getUserBasket_WhenProductBasketHasItems_ReturnsCorrectUserBasket() {

        UUID productIdOne = UUID.randomUUID();
        UUID productIdTwo = UUID.randomUUID();

        Product product1 = new SimpleProduct(productIdOne, "Product 1", 100);
        Product product2 = new DiscountedProduct(productIdTwo, "Product 2", 200, 10);

        Map<UUID, Integer> basketMap = new HashMap<>();
        basketMap.put(productIdOne, 2);
        basketMap.put(productIdTwo, 1);

        when(productBasket.getBasket()).thenReturn(basketMap);
        when(storageService.getProductById(productIdOne)).thenReturn(Optional.of(product1));
        when(storageService.getProductById(productIdTwo)).thenReturn(Optional.of(product2));


        UserBasket result = basketService.getUseBasket();


        assertNotNull(result);
        assertEquals(2, result.getBasketItems().size());
        assertEquals(380, result.getTotal());


        List<BasketItem> basketItems = result.getBasketItems();
        BasketItem item1 = findBasketItemByProductId(basketItems, productIdOne);
        BasketItem item2 = findBasketItemByProductId(basketItems, productIdTwo);

        assertNotNull(item1);
        assertNotNull(item2);
        assertEquals(2, item1.getItem());
        assertEquals(1, item2.getItem());
        assertEquals(product1, item1.getProduct());
        assertEquals(product2, item2.getProduct());
    }

    /**
     * getUserBasket выбрасывает исключение,
     * если товар из корзины не найден в StorageService
     */

    @Test
    void getUserBasket_WhenProductInBasketNotFoundInStorage_ThrowsNoSuchProductException() {

        UUID missingProductId = UUID.randomUUID();
        Map<UUID, Integer> basketMap = new HashMap<>();
        basketMap.put(missingProductId, 1);

        when(productBasket.getBasket()).thenReturn(basketMap);
        when(storageService.getProductById(missingProductId)).thenReturn(Optional.empty());


        assertThrows(NoSuchProductException.class,
                () -> basketService.getUseBasket());
    }

    /**
     * Проверка корректности расчета для разных типов продуктов
     */

    @Test
    void getUserBasket_WithDifferentProductTypes_CalculatesTotalCorrectly() {

        UUID simpleProductId = UUID.randomUUID();
        UUID discountedProductId = UUID.randomUUID();
        UUID fixPriceProductId = UUID.randomUUID();

        Product simpleProduct = new SimpleProduct(simpleProductId, "Simple", 150);
        Product discountedProduct = new DiscountedProduct(discountedProductId, "Discounted", 200, 25);
        Product fixPriceProduct = new FixPriceProduct(fixPriceProductId, "FixPrice");

        Map<UUID, Integer> basketMap = new HashMap<>();
        basketMap.put(simpleProductId, 1);
        basketMap.put(discountedProductId, 2);
        basketMap.put(fixPriceProductId, 1);

        when(productBasket.getBasket()).thenReturn(basketMap);
        when(storageService.getProductById(simpleProductId)).thenReturn(Optional.of(simpleProduct));
        when(storageService.getProductById(discountedProductId)).thenReturn(Optional.of(discountedProduct));
        when(storageService.getProductById(fixPriceProductId)).thenReturn(Optional.of(fixPriceProduct));


        UserBasket result = basketService.getUseBasket();


        assertNotNull(result);
        assertEquals(3, result.getBasketItems().size());


        assertTrue(result.getTotal() > 0);
    }

    /**
     * Проверка многократного добавления одного товара
     */

    @Test
    void productById_WhenCalledMultipleTimesForSameProduct_IncrementsQuantity() {

        when(storageService.getProductById(existingProductId))
                .thenReturn(Optional.of(existingProduct));


        basketService.productById(existingProductId);
        basketService.productById(existingProductId);
        basketService.productById(existingProductId);


        verify(productBasket, times(3)).mapBasket(existingProductId);
    }

    /**
     * Проверка неизменяемости возвращаемой корзины
     */

    @Test
    void getUserBasket_ReturnsUnmodifiableBasketItems() {

        when(productBasket.getBasket()).thenReturn(Collections.emptyMap());


        UserBasket result = basketService.getUseBasket();


        assertThrows(UnsupportedOperationException.class,
                () -> result.getBasketItems().add(new BasketItem(existingProduct, 1)));
    }

    /**
     * Вспомогательный метод для поиска BasketItem по productId
     */

    private BasketItem findBasketItemByProductId(List<BasketItem> basketItems, UUID productId) {
        return basketItems.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

}
