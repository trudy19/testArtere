package com.artereTest.aertereTest.controllers;


import com.artereTest.aertereTest.dtos.CartDTO;
import com.artereTest.aertereTest.dtos.CartItemDTO;
import com.artereTest.aertereTest.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/")
    public ResponseEntity<CartDTO> createCart() {
        CartDTO newCart = cartService.createCart(); // Méthode à implémenter dans le service
        return ResponseEntity.ok(newCart);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getCart(cartId));
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<CartDTO> addItemToCart(@PathVariable Long cartId, @RequestBody CartItemDTO cartItemDTO) {
        return ResponseEntity.ok(cartService.addItemToCart(cartId, cartItemDTO));
    }

    @PutMapping("/{cartId}/update")
    public ResponseEntity<CartDTO> updateItemInCart(@PathVariable Long cartId, @RequestBody CartItemDTO cartItemDTO) {
        return ResponseEntity.ok(cartService.updateItemInCart(cartId, cartItemDTO));
    }

    @DeleteMapping("/{cartId}/remove/{productId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.removeItemFromCart(cartId, productId);
        return ResponseEntity.noContent().build();
    }
}
