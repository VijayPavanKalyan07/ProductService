package org.pavan.productservice.services;

import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.models.Category;
import org.pavan.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductImplementation implements ProductService
{

    private RestClient restClient;

    public FakeStoreProductImplementation(RestClient restClient)
    {
        this.restClient = restClient;
    }

    @Override
    public List<Product> getAllProducts() {
        ProductDto[] productDtos = restClient.get().uri("/products").retrieve().body(ProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(ProductDto productDto: productDtos)
        {
            Product product = new Product();
            product.setId(productDto.getId());
            product.setPrice(productDto.getPrice());
            Category category = new Category();
            category.setName(productDto.getCategory());
            product.setCategory(category);
            product.setImageUrl(productDto.getImage());
            product.setTitle(productDto.getTitle());

            products.add(product);
        }
        return products;
    }

    /*
        Return a product object with all the details of the fetched product
        The ID of the category will be null but the name of the category shall be correct
     */

    @Override
    public Product getSingleProduct(Long productId) {

//        ResponseEntity<ProductDto> response = restClient
//                .get()
//                .uri("/products/{productId}", productId)
//                .retrieve()
//                .toEntity(ProductDto.class);
//
//        ProductDto productDto = response.getBody();

        ProductDto productDto = restClient.get().uri("/products/{productId}",productId).retrieve().body(ProductDto.class);

        Product product = new Product();

        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());

        Category category = new Category();
        category.setName(productDto.getCategory());

        product.setCategory(category);

        return product;
    }

    @Override
    public Product addNewProduct(ProductDto productdto) {
        ProductDto productDto = restClient.post().uri("/products").body(productdto).retrieve().body(ProductDto.class);

        // Convert DTO -> Entity
        Product newProduct = new Product();
        newProduct.setId(productDto.getId());
        newProduct.setTitle(productDto.getTitle());
        newProduct.setDescription(productDto.getDescription());
        newProduct.setPrice(productDto.getPrice());

        Category category = new Category();
        category.setName(productDto.getCategory());

        newProduct.setCategory(category);
        newProduct.setImageUrl(productDto.getImage());

        return newProduct;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
