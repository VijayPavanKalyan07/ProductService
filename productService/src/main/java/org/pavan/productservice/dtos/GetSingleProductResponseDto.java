package org.pavan.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.pavan.productservice.models.Product;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class GetSingleProductResponseDto
{
    private Product product;
}
