package com.artereTest.aertereTest.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "products")
@Data // Lombok va générer getters, setters, equals, hashCode, toString
@NoArgsConstructor // Génère un constructeur sans arguments
@AllArgsConstructor // Génère un constructeur avec tous les champs
public final class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Integer stockQuantity;
}
