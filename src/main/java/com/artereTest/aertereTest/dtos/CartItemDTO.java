package com.artereTest.aertereTest.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    private Long productId;
    private int quantity;
    private Double price;
}
