package com.koi151.QTDL.repository.custom;

import com.koi151.QTDL.entity.ProductCategory;
import com.koi151.QTDL.model.request.search.ProductCategorySearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCategoryCustomRepository {
    Page<ProductCategory> searchCategories(ProductCategorySearchRequest request, Pageable pageable);
}
