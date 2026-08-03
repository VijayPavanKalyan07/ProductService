package org.pavan.productservice.services;

import org.pavan.productservice.dtos.CategoryDto;
import org.pavan.productservice.dtos.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    List<ProductDto> getProductsInCategory(long categoryId);
}
