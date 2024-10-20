package com.artereTest.aertereTest.services;

import com.artereTest.aertereTest.Exceptions.CategoryNotFoundException;
import com.artereTest.aertereTest.Exceptions.ProductNotFoundException;
import com.artereTest.aertereTest.dtos.CategoryDTO;
import com.artereTest.aertereTest.dtos.ProductDTO;
import com.artereTest.aertereTest.models.Category;
import com.artereTest.aertereTest.models.Product;
import com.artereTest.aertereTest.repositories.CategoryRepository;
import com.artereTest.aertereTest.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = mapToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return mapToDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO updatedCategoryDTO) {
        if (categoryRepository.existsById(id)) {
            Category category = mapToEntity(updatedCategoryDTO);
            category.setId(id);
            Category savedCategory = categoryRepository.save(category);
            return mapToDTO(savedCategory);
        } else {
            throw new CategoryNotFoundException(id);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return mapToDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        return categories.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO addProductToCategory(Long categoryId, ProductDTO productDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        Product product = mapToEntity(productDTO);
        Product savedProduct = productRepository.save(product);

        category.getProducts().add(savedProduct);
        categoryRepository.save(category);

        return mapToDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProductInCategory(Long categoryId, Long productId, ProductDTO updatedProductDTO) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        if (productRepository.existsById(productId)) {
            Product product = mapToEntity(updatedProductDTO);
            product.setId(productId);
            Product savedProduct = productRepository.save(product);
            return mapToDTO(savedProduct);
        } else {
            throw new ProductNotFoundException(productId);
        }
    }

    @Override
    public void deleteProductFromCategory(Long categoryId, Long productId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        productRepository.deleteById(productId);
        category.getProducts().removeIf(product -> product.getId().equals(productId));
        categoryRepository.save(category);
    }

    @Override
    public ProductDTO getProductFromCategory(Long categoryId, Long productId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found in category " + categoryId));

        return mapToDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProductsFromCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        return category.getProducts().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private CategoryDTO mapToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setSubCategories(category.getSubCategories()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()));
        categoryDTO.setProducts(category.getProducts()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()));
        return categoryDTO;
    }

    private Category mapToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setSubCategories(categoryDTO.getSubCategories()
                .stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList()));
        category.setProducts(categoryDTO.getProducts()
                .stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList()));
        return category;
    }

    private ProductDTO mapToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setStockQuantity(product.getStockQuantity());
        return productDTO;
    }

    private Product mapToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        return product;
    }
}
