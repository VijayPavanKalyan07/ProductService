package org.pavan.productservice.services;

import org.pavan.productservice.dtos.CategoryDto;
import org.pavan.productservice.dtos.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreCategoryImplementation implements CategoryService {

    private final RestClient restClient;

    public FakeStoreCategoryImplementation(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        String[] categories = restClient.get()
                .uri("/products/categories")
                .retrieve()
                .body(String[].class);
        List<CategoryDto> result = new ArrayList<>();
        for (String category : categories) {
            CategoryDto dto = new CategoryDto();
            dto.setName(category);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<ProductDto> getProductsInCategory(String categoryName) {
        ProductDto[] productDtos = restClient.get()
                .uri("/products/category/{categoryName}", categoryName)
                .retrieve()
                .body(ProductDto[].class);
        return Arrays.asList(productDtos);
    }
}
