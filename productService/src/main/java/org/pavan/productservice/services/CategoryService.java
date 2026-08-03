package org.pavan.productservice.services;

import org.pavan.productservice.dtos.CategoryDto;
import org.pavan.productservice.dtos.ProductDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();

    List<ProductDto> getProductsInCategory(String categoryName);
}
