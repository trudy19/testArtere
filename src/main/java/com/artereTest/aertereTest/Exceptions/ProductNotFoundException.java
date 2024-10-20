package com.artereTest.aertereTest.Exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("Aucun produit trouvé avec l'id : " + productId);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
