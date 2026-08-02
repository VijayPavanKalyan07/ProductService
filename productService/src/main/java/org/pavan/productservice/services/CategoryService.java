package org.pavan.productservice.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CategoryService {

    String getAllCategories();

    String getProductsInCategory(long categoryId);
}
