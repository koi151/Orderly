package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityDeletionFailed;
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
        ProductCategory category = ProductCategory.builder()
            .categoryName(request.getCategoryName())
            .build();

        ProductCategory categorySaved = productCategoryRepository.save(category);
        return productCategoryMapper.toCategoryDTO(categorySaved);
    }

    @Override
    @Transactional
    public void deleteCategory (Long id) {
        ProductCategory existedCategory = productCategoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy loại SP với mã loại: " + id));

        Long productsCnt = productCategoryRepository.countProductsByCategoryId(id);

        if (productsCnt == 0) {
            existedCategory.setDeleted(true);
            productCategoryRepository.save(existedCategory);
        } else {
            throw new EntityDeletionFailed("ID danh mục đã được sử dụng bởi " + productsCnt
                + " sản phẩm. Sửa danh mục các sản phẩm trên trước khi xóa danh mục với id: " + id);
        }
    }
}
