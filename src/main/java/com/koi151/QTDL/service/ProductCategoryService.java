package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.ProductCategoryDTO;
import com.koi151.QTDL.model.request.create.ProductCategoryCreateRequest;
import com.koi151.QTDL.model.request.update.ProductCategoryUpdateRequest;

public interface ProductCategoryService {
    ProductCategoryDTO createCategory(ProductCategoryCreateRequest request);
    ProductCategoryDTO updateCategory(Long id, ProductCategoryUpdateRequest request);
    void deleteCategory (Long id);
}
