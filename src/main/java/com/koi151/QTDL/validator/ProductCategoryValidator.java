package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.model.request.ProductCategoryCreateRequest;
import com.koi151.QTDL.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCategoryValidator {

    private final ProductCategoryRepository productCategoryRepository;

    public void validateProductCategory(ProductCategoryCreateRequest request) {
        if (productCategoryRepository.existsByCategoryName(request.getCategoryName()))
            throw new InvalidRequestException("Tên loại sản phẩm đã tồn tại");
    }

}
