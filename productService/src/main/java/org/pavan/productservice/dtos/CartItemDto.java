package org.pavan.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private long productId;
    private int quantity;
}
