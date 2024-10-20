package com.artereTest.aertereTest.services;

import com.artereTest.aertereTest.dtos.CategoryDTO;
import com.artereTest.aertereTest.models.Category;
import com.artereTest.aertereTest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Books");
        categoryDTO.setDescription("Category for book products");

        when(categoryRepository.save(any(Category.class))).thenAnswer(invocation -> {
            Category category = invocation.getArgument(0);
            category.setId(1L); // Simuler la génération d'ID
            return category;
        });

        CategoryDTO savedCategory = categoryService.addCategory(categoryDTO);

        assertThat(savedCategory.getId()).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo("Books");
    }

    @Test
    void testUpdateCategory() {
        Category existingCategory = new Category();
        existingCategory.setId(1L);
        existingCategory.setName("Toys");
        existingCategory.setDescription("Category for toys");

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(any(Category.class))).thenReturn(existingCategory);

        CategoryDTO updatedCategoryDTO = new CategoryDTO();
        updatedCategoryDTO.setName("Updated Toys");
        updatedCategoryDTO.setDescription("Updated category for toys");

        CategoryDTO updatedCategory = categoryService.updateCategory(1L, updatedCategoryDTO);

        assertThat(updatedCategory.getName()).isEqualTo("Updated Toys");
    }

    @Test
    void testGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Electronics", "Category for electronic products", null, null));
        categories.add(new Category(2L, "Books", "Category for book products", null, null));

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> allCategories = categoryService.getAllCategories();

        assertThat(allCategories).hasSize(2);
        assertThat(allCategories.get(0).getName()).isEqualTo("Electronics");
    }

    @Test
    void testDeleteCategory() {
        Category existingCategory = new Category();
        existingCategory.setId(1L);
        existingCategory.setName("Toys");
        existingCategory.setDescription("Category for toys");

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(existingCategory));

        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
