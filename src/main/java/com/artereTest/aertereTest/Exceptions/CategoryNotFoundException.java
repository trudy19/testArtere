package com.artereTest.aertereTest.Exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long categoryId) {
        super("Aucune catégorie trouvée avec l'id : " + categoryId);
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
