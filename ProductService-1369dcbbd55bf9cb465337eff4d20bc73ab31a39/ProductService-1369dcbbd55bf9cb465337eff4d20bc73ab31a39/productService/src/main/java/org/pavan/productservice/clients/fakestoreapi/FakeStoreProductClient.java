package org.pavan.productservice.clients.fakestoreapi;

import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.models.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreProductClient {

    private final RestClient restClient;

    public FakeStoreProductClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<ProductDto> getAllProducts() {

        ProductDto[] products = restClient.get()
                .uri("/products")
                .retrieve()
                .body(ProductDto[].class);

        return Arrays.asList(products);
    }

    public Optional<ProductDto> getSingleProduct(Long productId) {

        ProductDto product = restClient.get()
                .uri("/products/{productId}", productId)
                .retrieve()
                .body(ProductDto.class);

        return Optional.ofNullable(product);
    }

    public ProductDto addNewProduct(ProductDto productDto) {

        return restClient.post()
                .uri("/products")
                .body(productDto)
                .retrieve()
                .body(ProductDto.class);
    }

    public ProductDto updateProduct(Long productId, Product product) {

        ProductDto dto = new ProductDto();

        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImageUrl());

        if (product.getCategory() != null) {
            dto.setCategory(product.getCategory().getName());
        }

        return restClient.patch()
                .uri("/products/{productId}", productId)
                .body(dto)
                .retrieve()
                .body(ProductDto.class);
    }

    public ProductDto replaceProduct(Long productId, Product product) {

        ProductDto dto = new ProductDto();

        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImageUrl());

        if (product.getCategory() != null) {
            dto.setCategory(product.getCategory().getName());
        }

        return restClient.put()
                .uri("/products/{productId}", productId)
                .body(dto)
                .retrieve()
                .body(ProductDto.class);
    }

    public boolean deleteProduct(Long productId) {

        restClient.delete()
                .uri("/products/{productId}", productId)
                .retrieve()
                .toBodilessEntity();

        return true;
    }
}