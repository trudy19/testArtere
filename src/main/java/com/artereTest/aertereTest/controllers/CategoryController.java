package com.artereTest.aertereTest.controllers;


import com.artereTest.aertereTest.dtos.CategoryDTO;
import com.artereTest.aertereTest.dtos.ProductDTO;
import com.artereTest.aertereTest.services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO category) {
        CategoryDTO newCategory = categoryService.addCategory(category);
        log.info("New category: {}" , newCategory);
        return ResponseEntity.ok(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO category) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, category);
        log.info("Updated category: {}" , updatedCategory);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        log.info("Deleted category: {}" , id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        log.info("Category: {}" , category);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/{categoryId}/products")
    public ResponseEntity<ProductDTO> addProductToCategory(@PathVariable Long categoryId, @RequestBody ProductDTO product) {
        ProductDTO newProduct = categoryService.addProductToCategory(categoryId, product);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<ProductDTO> updateProductInCategory(@PathVariable Long categoryId, @PathVariable Long productId, @RequestBody ProductDTO product) {
        ProductDTO updatedProduct = categoryService.updateProductInCategory(categoryId, productId, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<Void> deleteProductFromCategory(@PathVariable Long categoryId, @PathVariable Long productId) {
        categoryService.deleteProductFromCategory(categoryId, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<ProductDTO> getProductFromCategory(@PathVariable Long categoryId, @PathVariable Long productId) {
        ProductDTO product = categoryService.getProductFromCategory(categoryId, productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDTO>> getAllProductsFromCategory(@PathVariable Long categoryId) {
        List<ProductDTO> products = categoryService.getAllProductsFromCategory(categoryId);
        return ResponseEntity.ok(products);
    }

}
