package com.artereTest.aertereTest.services;


import com.artereTest.aertereTest.Exceptions.CartItemNotFoundException;
import com.artereTest.aertereTest.Exceptions.CartNotFoundException;
import com.artereTest.aertereTest.Exceptions.ProductNotFoundException;
import com.artereTest.aertereTest.dtos.CartDTO;
import com.artereTest.aertereTest.dtos.CartItemDTO;
import com.artereTest.aertereTest.models.Cart;
import com.artereTest.aertereTest.models.CartItem;
import com.artereTest.aertereTest.models.Product;
import com.artereTest.aertereTest.repositories.CartRepository;
import com.artereTest.aertereTest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;


    public CartDTO createCart() {
        Cart cart = new Cart();
        Cart savedCart = cartRepository.save(cart);
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(savedCart.getId());
        return cartDTO;
    }

    public CartDTO getCart(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        /// thorw exception si il est null
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            CartDTO cartDTO = new CartDTO();
            cartDTO.setId(cart.getId());
            cartDTO.setTotalAmount(cart.getTotalAmount());
            cartDTO.setItems(
                    cart.getCartItems().stream().map(this::convertToDTO).toList()
            );
            return cartDTO;
        }
        return null;
    }

    public CartDTO addItemToCart(Long cartId, CartItemDTO cartItemDTO) {
        Cart cart = cartRepository.findById(cartId).orElse(new Cart());/// speraritoon de concept
        Product product = productRepository.findById(cartItemDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setCart(cart);
        cart.getCartItems().add(cartItem);
        cartRepository.save(cart);
        return getCart(cart.getId());
    }

    public CartDTO updateItemInCart(Long cartId, CartItemDTO cartItemDTO) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart not found"));
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(cartItemDTO.getProductId()))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found"));

        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartRepository.save(cart);

        return getCart(cart.getId());
    }

    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart not found"));
        cart.getCartItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cartRepository.save(cart);
    }

    private CartItemDTO convertToDTO(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO();
        dto.setProductId(cartItem.getProduct().getId());
        dto.setQuantity(cartItem.getQuantity());
        dto.setPrice(cartItem.getSubtotal());
        return dto;
    }

}
