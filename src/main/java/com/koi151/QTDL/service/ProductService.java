package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.ProductCreateDTO;
import com.koi151.QTDL.model.request.ProductCreateRequest;

public interface ProductService {
    ProductCreateDTO createProduct(ProductCreateRequest request);
    void deleteProduct(Long id);
}
