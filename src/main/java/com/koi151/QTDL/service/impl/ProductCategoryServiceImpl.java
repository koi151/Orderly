package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.ProductCategory;
import com.koi151.QTDL.mapper.ProductCategoryMapper;
import com.koi151.QTDL.model.dto.ProductCategoryCreateDTO;
import com.koi151.QTDL.model.request.ProductCategoryCreateRequest;
import com.koi151.QTDL.repository.ProductCategoryRepository;
import com.koi151.QTDL.service.ProductCategoryService;
import com.koi151.QTDL.validator.ProductCategoryValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductCategoryValidator productCategoryValidator;

    @Override
    @Transactional
    public ProductCategoryCreateDTO createCategory(ProductCategoryCreateRequest request) {
        productCategoryValidator.validateProductCategory(request);
        ProductCategory loaiSP = ProductCategory.builder()
            .categoryName(request.getCategoryName())
            .build();

        ProductCategory loaiSPsaved = productCategoryRepository.save(loaiSP);
        return productCategoryMapper.toCategoryDTO(loaiSPsaved);
    }

    @Override
    @Transactional
    public void deleteCategory (Long id) {
        ProductCategory loaiSP = productCategoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy loại SP với mã loại: " + id));

//        var SPThuocLoai = categoryRepository.find

        loaiSP.setDeleted(true);
        productCategoryRepository.save(loaiSP);
    }
}
