package com.artereTest.aertereTest.Exceptions;


public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(Long cartId) {
        super("Aucune cart trouvée avec l'id : " + cartId);
    }

    public CartNotFoundException(String message) {
        super(message);
    }
}
