package com.artereTest.aertereTest.repository;

import com.artereTest.aertereTest.models.Category;
import com.artereTest.aertereTest.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateAndFindCategory() {
        Category category = new Category();
        category.setName("Books");
        category.setDescription("Category for book products");

        categoryRepository.save(category);

        Optional<Category> foundCategory = categoryRepository.findById(category.getId());
        assertThat(foundCategory).isPresent();
        assertThat(foundCategory.get().getName()).isEqualTo("Books");
    }

    @Test
    public void testDeleteCategory() {
        Category category = new Category();
        category.setName("Electronics");
        category.setDescription("Category for electronic products");
        categoryRepository.save(category);

        assertThat(categoryRepository.findById(category.getId())).isPresent();

        categoryRepository.deleteById(category.getId());

        assertThat(categoryRepository.findById(category.getId())).isNotPresent();
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        category.setName("Toys");
        category.setDescription("Category for toys");
        categoryRepository.save(category);

        category.setName("Updated Toys");
        categoryRepository.save(category);

        Optional<Category> updatedCategory = categoryRepository.findById(category.getId());
        assertThat(updatedCategory).isPresent();
        assertThat(updatedCategory.get().getName()).isEqualTo("Updated Toys");
    }
}
