package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.ProductCategoryDTO;
import com.koi151.QTDL.model.request.ProductCategoryRequest;

public interface ProductCategoryService {
    ProductCategoryDTO createCategory(ProductCategoryRequest request);
    ProductCategoryDTO updateCategory(Long id, ProductCategoryRequest request);
    void deleteCategory (Long id);
}
