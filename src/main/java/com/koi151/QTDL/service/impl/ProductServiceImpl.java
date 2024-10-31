package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityAlreadyExistsException;
import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.Product;
import com.koi151.QTDL.entity.ProductCategory;
import com.koi151.QTDL.entity.Supplier;
import com.koi151.QTDL.mapper.ProductMapper;
import com.koi151.QTDL.model.dto.ProductCreateDTO;
import com.koi151.QTDL.model.request.ProductCreateRequest;
import com.koi151.QTDL.repository.ProductCategoryRepository;
import com.koi151.QTDL.repository.SupplierRepository;
import com.koi151.QTDL.repository.ProductRepository;
import com.koi151.QTDL.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductCreateDTO createProduct(ProductCreateRequest request) {
        if (productRepository.existsByProductName(request.getProductName()))
            throw new EntityAlreadyExistsException("Tên sản phẩm đã được sử dụng");

        ProductCategory productCategory = productCategoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy loại sản phẩm với mã: " + request.getCategoryId()));

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhà cung cấp với mã: " + request.getSupplierId()));

        Product sp = Product.builder()
            .productName(request.getProductName())
            .supplier(supplier)
            .productCategory(productCategory)
            .price(request.getPrice())
            .build();

        Product savedSPEntity = productRepository.save(sp);
        return ProductCreateDTO.builder()
            .productName(savedSPEntity.getProductName())
            .supplierName(savedSPEntity.getSupplier().getSupplierName())
            .categoryName(savedSPEntity.getProductCategory().getCategoryName())
            .price(savedSPEntity.getPrice())
            .build();
    }

    @Override
    public void deleteProduct(Long id) {
        Product sp = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại sản phẩm với id: " + id));

        sp.setDeleted(true);
        productRepository.save(sp);
    }
}
