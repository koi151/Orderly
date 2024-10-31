package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.model.request.SupplierRequest;
import com.koi151.QTDL.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplierValidator {

    private final SupplierRepository supplierRepository;

    public void validateSupplier(SupplierRequest request) {
        if (supplierRepository.existsBySupplierName(request.getSupplierName()))
            throw new InvalidRequestException("Tên nhà cung cấp đã tồn tại");
    }

}
