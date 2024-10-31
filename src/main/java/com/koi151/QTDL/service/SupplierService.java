package com.koi151.QTDL.service;

import com.koi151.QTDL.entity.Supplier;
import com.koi151.QTDL.model.dto.SupplierDTO;
import com.koi151.QTDL.model.request.SupplierRequest;

public interface SupplierService {
    SupplierDTO createSupplier(SupplierRequest request);
    SupplierDTO updateSupplier(Long supplierId, SupplierRequest request);

    void deleteSupplier(Long id);
}
