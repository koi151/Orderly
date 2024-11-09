package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityDeletionFailed;
import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.Supplier;
import com.koi151.QTDL.mapper.SupplierMapper;
import com.koi151.QTDL.model.dto.SupplierDTO;
import com.koi151.QTDL.model.request.SupplierRequest;
import com.koi151.QTDL.repository.SupplierRepository;
import com.koi151.QTDL.service.SupplierService;
import com.koi151.QTDL.validator.SupplierValidator;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final SupplierValidator supplierValidator;

//    @Override
//    @Transactional
//    public SupplierDTO createSupplier(SupplierRequest request) {
//        supplierValidator.validateSupplier(request);
//
//        Supplier ncc = Supplier.builder()
//            .supplierName(request.getSupplierName())
//            .address(request.getAddress())
//            .repInfo(request.getRepInfo())
//            .build();
//
//        Supplier savedNCCEntity = supplierRepository.save(ncc);
//        return supplierMapper.toSupplierCreateDTO(savedNCCEntity);
//    }

    @Override
    public SupplierDTO createSupplier(SupplierRequest request) {
        // Gọi stored procedure để thêm nhà cung cấp mới và nhận supplier_id vừa tạo
        Long supplierId = supplierRepository.createSupplier(
            request.getSupplierName(),
            request.getAddress(),
            request.getRepInfo()
        );

        // Trả về đối tượng SupplierDTO
        return SupplierDTO.builder()
            .supplierId(supplierId)
            .supplierName(request.getSupplierName())
            .address(request.getAddress())
            .repInfo(request.getRepInfo())
            .build();
    }

//    @Transactional
//    public SupplierDTO updateSupplier(Long supplierId, SupplierRequest supplierRequest) {
//        Supplier existingSupplier = supplierRepository.findById(supplierId)
//            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhà cung cấp với id: " + supplierId));
//
//        Supplier updatedSupplier = supplierMapper.updateSupplierFromRequest(supplierRequest, existingSupplier);
//        Supplier savedSupplier = supplierRepository.save(updatedSupplier);
//        return supplierMapper.toSupplierDTO(savedSupplier);
//    }

    @Transactional
    public SupplierDTO updateSupplier(Long supplierId, SupplierRequest supplierRequest) {
        // Kiểm tra xem nhà cung cấp có tồn tại không
        Supplier existingSupplier = supplierRepository.findById(supplierId)
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhà cung cấp với id: " + supplierId));

        // Gọi stored procedure để cập nhật thông tin nhà cung cấp
        supplierRepository.updateSupplier(
            supplierId,
            supplierRequest.getSupplierName(),
            supplierRequest.getAddress(),
            supplierRequest.getRepInfo()
        );

//        // Sử dụng flush và clear để đảm bảo thay đổi được lưu ngay lập tức và làm mới cache
//        entityManager.flush();
//        entityManager.clear();

        // Lấy lại thông tin nhà cung cấp đã cập nhật từ cơ sở dữ liệu
        Supplier updatedSupplier = supplierRepository.findById(supplierId)
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhà cung cấp với id: " + supplierId));

        // Trả về đối tượng SupplierDTO
        return supplierMapper.toSupplierDTO(updatedSupplier);
    }


    @Override
    @Transactional
    public void deleteSupplier(Long id) {
        Supplier existedSupplier = supplierRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhà cung cấp với id: " + id));

        Long productsCnt = supplierRepository.countProductsBySupplierId(id);

        if (productsCnt == 0) {
            existedSupplier.setDeleted(true);
            supplierRepository.save(existedSupplier);
        } else {
            throw new EntityDeletionFailed("ID nhà cung cấp đã được sử dụng bởi " + productsCnt
                + " sản phẩm. Sửa nhà cung cấp các sản phẩm trên trước khi xóa nhà cung cấp với id: " + id);
        }
    }
}
