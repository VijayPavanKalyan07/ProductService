package org.pavan.productservice.services;

import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.exceptions.ProductNotFoundException;
import org.pavan.productservice.mappers.ProductMapper;
import org.pavan.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@Service
public class FakeStoreProductImplementation implements ProductService {

    private final RestClient restClient;

    public FakeStoreProductImplementation(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<Product> getAllProducts() {
        ProductDto[] productDtos = restClient.get()
                .uri("/products")
                .retrieve()
                .body(ProductDto[].class);
        return ProductMapper.toProductList(productDtos);
    }

    @Override
    public List<Product> getProductsWithLimit(int limit) {
        ProductDto[] productDtos = restClient.get()
                .uri("/products?limit={limit}", limit)
                .retrieve()
                .body(ProductDto[].class);
        return ProductMapper.toProductList(productDtos);
    }

    @Override
    public List<Product> getProductsSorted(String sort) {
        ProductDto[] productDtos = restClient.get()
                .uri("/products?sort={sort}", sort)
                .retrieve()
                .body(ProductDto[].class);
        return ProductMapper.toProductList(productDtos);
    }

    @Override
    public Product getSingleProduct(Long productId) {
        try {
            ProductDto productDto = restClient.get()
                    .uri("/products/{productId}", productId)
                    .retrieve()
                    .body(ProductDto.class);
            return ProductMapper.toProduct(productDto);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) {
                throw new ProductNotFoundException(productId);
            }
            throw ex;
        }
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        ProductDto[] productDtos = restClient.get()
                .uri("/products/category/{categoryName}", categoryName)
                .retrieve()
                .body(ProductDto[].class);
        return ProductMapper.toProductList(productDtos);
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        ProductDto response = restClient.post()
                .uri("/products")
                .body(productDto)
                .retrieve()
                .body(ProductDto.class);
        return ProductMapper.toProduct(response);
    }

    @Override
    public Product updateProduct(Long productId, ProductDto productDto) {
        try {
            ProductDto response = restClient.put()
                    .uri("/products/{productId}", productId)
                    .body(productDto)
                    .retrieve()
                    .body(ProductDto.class);
            return ProductMapper.toProduct(response);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) {
                throw new ProductNotFoundException(productId);
            }
            throw ex;
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        try {
            restClient.delete()
                    .uri("/products/{productId}", productId)
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) {
                throw new ProductNotFoundException(productId);
            }
            throw ex;
        }
    }
}
