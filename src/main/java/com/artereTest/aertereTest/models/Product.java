package com.artereTest.aertereTest.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Integer stockQuantity;
}
