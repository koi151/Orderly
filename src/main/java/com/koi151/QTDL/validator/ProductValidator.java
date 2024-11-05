package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductValidator {

    private final ProductRepository productRepository;

    public void validateProductName(String name) {
        if (productRepository.existsByProductName(name))
            throw new InvalidRequestException("Tên sản phẩm đã tồn tại");
    }

    public void validateUpdateProductName(String name, Long requestedId) {
        if (productRepository.existsByProductNameAndProductIdNot(name, requestedId))
            throw new InvalidRequestException("Tên sản phẩm đã tồn tại");
    }
}