package org.pavan.productservice.clients.fakestoreapi;

import org.pavan.productservice.dtos.CategoryDto;
import org.pavan.productservice.dtos.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreCategoryClient {

    private final RestClient restClient;

    public FakeStoreCategoryClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<CategoryDto> getAllCategories() {

        String[] categories = restClient.get()
                .uri("/products/categories")
                .retrieve()
                .body(String[].class);

        /*
        // RestTemplate Equivalent

        String[] categories =
                restTemplate.getForObject(
                        "/products/categories",
                        String[].class);
        */

        List<CategoryDto> result = new ArrayList<>();

        for (String category : categories) {
            CategoryDto dto = new CategoryDto();
            dto.setName(category);
            result.add(dto);
        }

        return result;
    }

    public List<ProductDto> getProductsInCategory(long categoryId) {

        ProductDto[] products = restClient.get()
                .uri("/categories/{categoryId}/products", categoryId)
                .retrieve()
                .body(ProductDto[].class);

        /*
        // RestTemplate Equivalent

        ProductDto[] products =
                restTemplate.getForObject(
                        "/categories/{categoryId}/products",
                        ProductDto[].class,
                        categoryId);
        */

        return Arrays.asList(products);
    }
}