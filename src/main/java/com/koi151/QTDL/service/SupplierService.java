package com.koi151.QTDL.service;

import com.koi151.QTDL.entity.Supplier;
import com.koi151.QTDL.model.dto.SupplierDTO;
import com.koi151.QTDL.model.request.SupplierRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {

    Page<SupplierDTO> findSuppliers(Pageable pageable);
    SupplierDTO createSupplier(SupplierRequest request);
    SupplierDTO updateSupplier(Long supplierId, SupplierRequest request);

    void deleteSupplier(Long id);
}
