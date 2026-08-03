package org.pavan.productservice.services;

import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getSingleProduct(Long productId);


    Product addNewProduct(ProductDto productdto);

    //Product updateProduct(Long productId, Product product);

    Product updateProduct(Long productId, Product productdto);

    Product replaceProduct(Long productId, Product productdto);


    boolean deleteProduct(Long productId);
}
