package org.pavan.productservice.services;

import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);


   Product addNewProduct(ProductDto productdto);


    /*
     Product object has only those fileds filled which need to be updated
     everything else is null
     */
    Product updateProduct(Long productId, Product product);


    boolean deleteProduct(Long productId);
}

// Update product with id 123
// {
//   name: iphone 15
// }