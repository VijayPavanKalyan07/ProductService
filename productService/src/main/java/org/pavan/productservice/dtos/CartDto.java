package org.pavan.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDto {
    private long id;
    private long userId;
    private String date;
    private List<CartItemDto> products;
}
