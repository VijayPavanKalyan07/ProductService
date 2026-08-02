package org.pavan.productservice.services;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class FakeStoreCategoryImplementation implements CategoryService
{

    @Override
    public String getAllCategories() {
        return "";
    }

    @Override
    public String getProductsInCategory(long categoryId) {
        return "";
    }
}
