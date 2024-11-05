package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.Product;
import com.koi151.QTDL.entity.ProductCategory;
import com.koi151.QTDL.entity.Supplier;
import com.koi151.QTDL.mapper.ProductMapper;
import com.koi151.QTDL.model.dto.ProductDTO;
import com.koi151.QTDL.model.request.create.ProductCreateRequest;
import com.koi151.QTDL.model.request.update.ProductUpdateRequest;
import com.koi151.QTDL.repository.ProductCategoryRepository;
import com.koi151.QTDL.repository.SupplierRepository;
import com.koi151.QTDL.repository.ProductRepository;
import com.koi151.QTDL.service.ProductService;
import com.koi151.QTDL.validator.ProductValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductValidator productValidator;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDTO createProduct(ProductCreateRequest request) {
        productValidator.validateProductName(request.getProductName());

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

        Product productSaved = productRepository.save(sp);
        return ProductDTO.builder()
            .productName(productSaved.getProductName())
            .supplierName(productSaved.getSupplier().getSupplierName())
            .categoryName(productSaved.getProductCategory().getCategoryName())
            .price(productSaved.getPrice())
            .build();
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Long id, ProductUpdateRequest request) {
        Product existingProduct = productRepository.findByProductIdAndDeleted(id, false)
                .orElseThrow(() -> new EntityNotExistedException("Không tồn tại sản phẩm với id: " + id));

        productValidator.validateUpdateProductName(request.getProductName(), id);

        if (request.getCategoryId() != null) {
            ProductCategory productCategory = productCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy loại sản phẩm với mã: " + request.getCategoryId()));
            existingProduct.setProductCategory(productCategory);
        }

        if (request.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhà cung cấp với mã: " + request.getSupplierId()));
            existingProduct.setSupplier(supplier);
        }

        // Use MapStruct to update non-null fields from request
        productMapper.updateProductFromRequest(request, existingProduct);

        Product savedProduct = productRepository.save(existingProduct);
        return productMapper.toProductDTO(savedProduct);
    }



    @Override
    public void deleteProduct(Long id) {
        Product sp = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại sản phẩm với id: " + id));

        sp.setDeleted(true);
        productRepository.save(sp);
    }
}
