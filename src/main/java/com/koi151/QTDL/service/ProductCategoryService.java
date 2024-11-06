package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.ProductCategoryDTO;
import com.koi151.QTDL.model.request.create.ProductCategoryCreateRequest;
import com.koi151.QTDL.model.request.search.ProductCategorySearchRequest;
import com.koi151.QTDL.model.request.update.ProductCategoryUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCategoryService {

    Page<ProductCategoryDTO> getCategories(ProductCategorySearchRequest request, Pageable pageable);
    ProductCategoryDTO createCategory(ProductCategoryCreateRequest request);
    ProductCategoryDTO updateCategory(Long id, ProductCategoryUpdateRequest request);
    void deleteCategory (Long id);
}
