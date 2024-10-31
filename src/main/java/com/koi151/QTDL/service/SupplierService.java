package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.SupplierCreateDTO;
import com.koi151.QTDL.model.request.SupplierCreateRequest;

public interface SupplierService {
    SupplierCreateDTO createSupplier(SupplierCreateRequest request);

    void deleteSupplier(Long id);
}
