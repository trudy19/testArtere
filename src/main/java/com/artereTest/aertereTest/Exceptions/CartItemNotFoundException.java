package com.artereTest.aertereTest.Exceptions;

public class CartItemNotFoundException extends RuntimeException  {

    public CartItemNotFoundException(Long cartItemId) {
        super("Aucune cart trouvée avec l'id : " + cartItemId);
    }

    public CartItemNotFoundException(String message) {
        super(message);
    }
}
