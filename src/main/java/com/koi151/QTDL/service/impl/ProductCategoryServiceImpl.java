package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityDeletionFailed;
import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.ProductCategory;
import com.koi151.QTDL.mapper.ProductCategoryMapper;
import com.koi151.QTDL.mapper.ProductMapper;
import com.koi151.QTDL.model.dto.ProductCategoryDTO;
import com.koi151.QTDL.model.request.create.ProductCategoryCreateRequest;
import com.koi151.QTDL.model.request.search.ProductCategorySearchRequest;
import com.koi151.QTDL.model.request.update.ProductCategoryUpdateRequest;
import com.koi151.QTDL.repository.ProductCategoryRepository;
import com.koi151.QTDL.service.ProductCategoryService;
import com.koi151.QTDL.validator.ProductCategoryValidator;
import com.koi151.QTDL.validator.ProductValidator;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductCategoryValidator productCategoryValidator;
    private final EntityManager entityManager;

//    @Override
//    public Page<ProductCategoryDTO> getCategories(PropertyCategorySearchRequest request, Pageable pageable) {
//        Page<ProductCategory> categories = productCategoryRepository.findByCategoryNameAndDeleted(request.getCategoryName(), false, pageable);
//        return productCategoryMapper.toProductCategoryPage(categories);
//    }

    @Override
    public Page<ProductCategoryDTO> getCategories(ProductCategorySearchRequest request, Pageable pageable) {
        return productCategoryRepository.searchCategories(request, pageable)
            .map(productCategoryMapper::toCategoryDTO);
    }

//    @Override
//    @Transactional
//    public ProductCategoryDTO createCategory(ProductCategoryCreateRequest request) {
//        productCategoryValidator.validateCategoryName(request.getCategoryName());
//
//        ProductCategory category = ProductCategory.builder()
//            .categoryName(request.getCategoryName())
//            .build();
//
//        ProductCategory categorySaved = productCategoryRepository.save(category);
//        return productCategoryMapper.toCategoryDTO(categorySaved);
//    }

    @Override
    public ProductCategoryDTO createCategory(ProductCategoryCreateRequest request) {
        // Gọi stored procedure để thêm danh mục mới và lấy categoryId vừa tạo
        Long categoryId = productCategoryRepository.createCategory(
            request.getCategoryName(),
            request.getDescription()
        );

        // Tạo đối tượng ProductCategoryDTO để trả về
        return ProductCategoryDTO.builder()
            .categoryId(categoryId)  // Sử dụng categoryId vừa tạo
            .categoryName(request.getCategoryName())
            .description(request.getDescription())
            .build();
    }

//    @Override
//    @Transactional
//    public ProductCategoryDTO updateCategory(Long categoryId, ProductCategoryUpdateRequest request) {
//        productCategoryValidator.validateUpdateCategoryName(request.getCategoryName(), categoryId);
//
//        ProductCategory existingCategory = productCategoryRepository.findByCategoryIdAndDeleted(categoryId, false)
//            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại danh mục với id: " + categoryId));
//
//        ProductCategory updatedCategory =  productCategoryMapper.updateCategoryFromRequest(request, existingCategory);
//        ProductCategory savedCategory = productCategoryRepository.save(updatedCategory);
//
//        return productCategoryMapper.toCategoryDTO(savedCategory);
//    }

    @Override
    public ProductCategoryDTO updateCategory(Long categoryId, ProductCategoryUpdateRequest request) {
        // Gọi stored procedure để cập nhật danh mục
        productCategoryRepository.updateCategory(
            categoryId,
            request.getCategoryName(),
            request.getDescription()
        );

        return ProductCategoryDTO.builder()
            .categoryId(categoryId)
            .categoryName(request.getCategoryName())
            .description(request.getDescription())
            .build();
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
