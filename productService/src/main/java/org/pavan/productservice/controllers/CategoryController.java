package org.pavan.productservice.controllers;

import org.pavan.productservice.dtos.CategoryDto;
import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryName}/products")
    public ResponseEntity<List<ProductDto>> getProductsInCategory(
            @PathVariable String categoryName) {
        return ResponseEntity.ok(categoryService.getProductsInCategory(categoryName));
    }
}
