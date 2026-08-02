package org.pavan.productservice.services;

import org.pavan.productservice.dtos.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class FakeStoreProductImplementation implements ProductService
{

    @Override
    public String getAllProducts() {
        return "";
    }

    @Override
    public String getSingleProduct(Long productId) {
        return "";
    }

    @Override
    public String addNewProduct(ProductDto productdto) {
        return "";
    }

    @Override
    public String updateProduct(Long productId) {
        return "";
    }

    @Override
    public String deleteProduct(Long productId) {
        return "";
    }
}
