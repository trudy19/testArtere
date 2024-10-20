package com.artereTest.aertereTest.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private List<CategoryDTO> subCategories;
    private List<ProductDTO> products;

}
