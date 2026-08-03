package org.pavan.productservice.services;

import org.pavan.productservice.clients.fakestoreapi.FakeStoreCategoryClient;
import org.pavan.productservice.dtos.CategoryDto;
import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreCategoryImplementation implements CategoryService {

    private final FakeStoreCategoryClient fakeStoreCategoryClient;

    public FakeStoreCategoryImplementation(FakeStoreCategoryClient fakeStoreCategoryClient) {
        this.fakeStoreCategoryClient = fakeStoreCategoryClient;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return fakeStoreCategoryClient.getAllCategories();
    }

    @Override
    public List<ProductDto> getProductsInCategory(long categoryId) {
        return fakeStoreCategoryClient.getProductsInCategory(categoryId);
    }
}