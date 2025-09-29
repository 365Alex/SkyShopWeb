package org.skypro.skyshop.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exception.NoSuchProductException;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

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
        Mockito.when(storageService.getProductById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () ->
        {basketService.productById(UUID.randomUUID());});

    }

}
