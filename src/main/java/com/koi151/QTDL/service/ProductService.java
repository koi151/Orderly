package com.koi151.QTDL.service;

import com.koi151.QTDL.entity.Product;
import com.koi151.QTDL.model.dto.ProductDTO;
import com.koi151.QTDL.model.dto.ProductDTODetails;
import com.koi151.QTDL.model.request.create.ProductCreateRequest;
import com.koi151.QTDL.model.request.search.ProductSearchRequest;
import com.koi151.QTDL.model.request.update.ProductUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDTO createProduct(ProductCreateRequest request);
    ProductDTO updateProduct(Long id, ProductUpdateRequest request);

    Page<ProductDTODetails> getProducts(ProductSearchRequest request, Pageable pageable);
    void deleteProduct(Long id);
}
