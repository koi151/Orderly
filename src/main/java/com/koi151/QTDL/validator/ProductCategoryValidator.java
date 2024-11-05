package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.model.request.update.ProductCategoryUpdateRequest;
import com.koi151.QTDL.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCategoryValidator {

    private final ProductCategoryRepository productCategoryRepository;

    public void validateCategoryName(String name) {
        if (productCategoryRepository.existsByCategoryName(name))
            throw new InvalidRequestException("Tên danh mục sản phẩm đã tồn tại");
    }

    public void validateUpdateCategoryName(String name, Long requestedId) {
        if (productCategoryRepository.existsByCategoryNameAndCategoryIdNot(name, requestedId))
            throw new InvalidRequestException("Tên danh mục sản phẩm đã tồn tại");
    }
}
