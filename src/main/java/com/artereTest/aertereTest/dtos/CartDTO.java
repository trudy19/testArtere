package com.artereTest.aertereTest.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDTO {
    private Long id;
    private List<CartItemDTO> items;
    private Double totalAmount;
}
