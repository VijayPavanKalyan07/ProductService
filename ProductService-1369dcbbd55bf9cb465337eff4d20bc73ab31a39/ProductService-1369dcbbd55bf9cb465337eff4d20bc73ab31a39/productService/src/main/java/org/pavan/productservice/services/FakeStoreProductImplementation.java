package org.pavan.productservice.services;

import org.pavan.productservice.clients.fakestoreapi.FakeStoreProductClient;
import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.models.Category;
import org.pavan.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductImplementation implements ProductService {

    private final FakeStoreProductClient fakeStoreProductClient;

    public FakeStoreProductImplementation(FakeStoreProductClient fakeStoreProductClient) {
        this.fakeStoreProductClient = fakeStoreProductClient;
    }

    private Product convertProductDtoToProduct(ProductDto productDto) {

        Product product = new Product();

        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImage());

        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public List<Product> getAllProducts() {

        List<ProductDto> productDtos = fakeStoreProductClient.getAllProducts();

        List<Product> products = new ArrayList<>();

        for (ProductDto dto : productDtos) {
            products.add(convertProductDtoToProduct(dto));
        }

        return products;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {

        Optional<ProductDto> productDto = fakeStoreProductClient.getSingleProduct(productId);

        if (productDto.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(convertProductDtoToProduct(productDto.get()));
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {

        ProductDto savedProductDto = fakeStoreProductClient.addNewProduct(productDto);

        return convertProductDtoToProduct(savedProductDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {

        ProductDto updatedProductDto = fakeStoreProductClient.updateProduct(productId, product);

        return convertProductDtoToProduct(updatedProductDto);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {

        ProductDto replacedProductDto = fakeStoreProductClient.replaceProduct(productId, product);

        return convertProductDtoToProduct(replacedProductDto);
    }

    @Override
    public boolean deleteProduct(Long productId) {

        return fakeStoreProductClient.deleteProduct(productId);
    }
}