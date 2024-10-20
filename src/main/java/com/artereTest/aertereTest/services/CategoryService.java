package com.artereTest.aertereTest.services;


import com.artereTest.aertereTest.dtos.CategoryDTO;
import com.artereTest.aertereTest.dtos.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    CategoryDTO addCategory(CategoryDTO category);
    CategoryDTO updateCategory(Long id, CategoryDTO category);
    void deleteCategory(Long id);
    CategoryDTO getCategoryById(Long id);
    List<CategoryDTO> getAllCategories();

    ProductDTO addProductToCategory(Long categoryId, ProductDTO product);
    ProductDTO updateProductInCategory(Long categoryId, Long productId, ProductDTO product);
    void deleteProductFromCategory(Long categoryId, Long productId);
    ProductDTO getProductFromCategory(Long categoryId, Long productId);
    List<ProductDTO> getAllProductsFromCategory(Long categoryId);
}
