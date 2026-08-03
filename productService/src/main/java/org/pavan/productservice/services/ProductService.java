package org.pavan.productservice.services;

import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getProductsWithLimit(int limit);

    List<Product> getProductsSorted(String sort);

    Product getSingleProduct(Long productId);

    List<Product> getProductsByCategory(String categoryName);

    Product addNewProduct(ProductDto productDto);

    Product updateProduct(Long productId, ProductDto productDto);

    void deleteProduct(Long productId);
}
