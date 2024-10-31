package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.ProductCategoryCreateDTO;
import com.koi151.QTDL.model.request.ProductCategoryCreateRequest;

public interface ProductCategoryService {
    ProductCategoryCreateDTO createCategory(ProductCategoryCreateRequest request);

    void deleteCategory (Long id);
}
