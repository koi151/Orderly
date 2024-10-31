package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.Supplier;
import com.koi151.QTDL.mapper.SupplierMapper;
import com.koi151.QTDL.model.dto.SupplierCreateDTO;
import com.koi151.QTDL.model.request.SupplierCreateRequest;
import com.koi151.QTDL.repository.SupplierRepository;
import com.koi151.QTDL.service.SupplierService;
import com.koi151.QTDL.validator.SupplierValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final SupplierValidator supplierValidator;

    @Override
    @Transactional
    public SupplierCreateDTO createSupplier(SupplierCreateRequest request) {
        supplierValidator.validateSupplier(request);

        Supplier ncc = Supplier.builder()
            .supplierName(request.getSupplierName())
            .address(request.getAddress())
            .repInfo(request.getRepInfo())
            .build();

        Supplier savedNCCEntity = supplierRepository.save(ncc);
        return supplierMapper.toSupplierCreateDTO(savedNCCEntity);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier ncc = supplierRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhà cung cấp với id: " + id));

        ncc.setDeleted(true);
        supplierRepository.save(ncc);
    }
}
