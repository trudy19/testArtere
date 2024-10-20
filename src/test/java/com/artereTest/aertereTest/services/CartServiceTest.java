package com.artereTest.aertereTest.services;

import com.artereTest.aertereTest.Exceptions.CartNotFoundException;
import com.artereTest.aertereTest.dtos.CartDTO;
import com.artereTest.aertereTest.dtos.CartItemDTO;
import com.artereTest.aertereTest.models.Cart;
import com.artereTest.aertereTest.repositories.CartRepository;
import com.artereTest.aertereTest.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;
    @Mock
    private ProductRepository productRepository;

    private Cart cart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cart = new Cart();
        cart.setId(1L);
        cart.setCartItems(new ArrayList<>());
    }

    @Test
    void createCart_ShouldReturnCartDTO() {
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        CartDTO result = cartService.createCart();

        assertNotNull(result);
        assertEquals(cart.getId(), result.getId());
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void getCart_ShouldReturnCartDTO() {
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        CartDTO result = cartService.getCart(cart.getId());
        assertNotNull(result);
        assertEquals(cart.getId(), result.getId());
        verify(cartRepository, times(1)).findById(cart.getId());
    }
    @Test
    void updateItemInCart_ShouldThrowCartNotFoundException() {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setProductId(1L);
        cartItemDTO.setQuantity(1);

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.empty());

        assertThrows(CartNotFoundException.class, () -> cartService.updateItemInCart(cart.getId(), cartItemDTO));
    }


}
