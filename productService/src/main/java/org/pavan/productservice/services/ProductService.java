package org.pavan.productservice.services;

import org.pavan.productservice.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

public interface ProductService {
    String getAllProducts();

    String getSingleProduct(Long productId);


    String addNewProduct(ProductDto productdto);


    String updateProduct(Long productId);


    String deleteProduct(Long productId);
}
