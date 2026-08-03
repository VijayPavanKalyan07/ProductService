package org.pavan.productservice.mappers;

import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.models.Category;
import org.pavan.productservice.models.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ProductMapper {

    private ProductMapper() {
    }

    public static Product toProduct(ProductDto dto) {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice() != null ? dto.getPrice() : 0.0);
        product.setImageUrl(dto.getImage());

        if (dto.getCategory() != null) {
            Category category = new Category();
            category.setName(dto.getCategory());
            product.setCategory(category);
        }
        return product;
    }

    public static ProductDto toDto(Product product) {
        if (product == null) {
            return null;
        }
        ProductDto dto = new ProductDto();
        dto.setId(product.getId() != null ? product.getId() : 0);
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImage(product.getImageUrl());
        if (product.getCategory() != null) {
            dto.setCategory(product.getCategory().getName());
        }
        return dto;
    }

    public static List<Product> toProductList(ProductDto[] dtos) {
        if (dtos == null) {
            return List.of();
        }
        return Arrays.stream(dtos).map(ProductMapper::toProduct).collect(Collectors.toList());
    }
}
