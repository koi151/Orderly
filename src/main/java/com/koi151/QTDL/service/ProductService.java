package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.ProductDTO;
import com.koi151.QTDL.model.request.create.ProductCreateRequest;
import com.koi151.QTDL.model.request.update.ProductUpdateRequest;

public interface ProductService {
    ProductDTO createProduct(ProductCreateRequest request);
    ProductDTO updateProduct(Long id, ProductUpdateRequest request);
    void deleteProduct(Long id);
}
