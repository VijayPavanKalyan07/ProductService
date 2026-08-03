package org.pavan.productservice.controllers;

import org.pavan.productservice.dtos.CategoryDto;
import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController
{
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryDto> getAllCategories()
    {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}/products")
    public List<ProductDto> getProductsInCategory(@PathVariable("categoryId") long categoryId)
    {
        return categoryService.getProductsInCategory(categoryId);
    }

    @GetMapping("/{categoryId}")
    public String getCategoryById(@PathVariable("categoryId") long categoryId)
    {
        return "Get Category By Id: " + categoryId;
    }

    @PostMapping()
    public String addNewCategory(@RequestBody CategoryDto categoryDto)
    {
           return "Added New Category " + categoryDto;
    }

    @PutMapping("/{categoryId}")
    public String updateCategory(@PathVariable("categoryId") long categoryId, @RequestBody CategoryDto categoryDto)
    {
        return  "Updated Category id: " + categoryId;
    }

    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") long categoryId)
    {
        return "Deleted Category id: "+ categoryId;
    }
}
