package org.pavan.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto
{
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;

}
