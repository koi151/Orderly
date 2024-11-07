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
import jakarta.persistence.EntityManager;
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
    private final EntityManager entityManager;


//    @Override
//    @Transactional
//    public ProductDTO createProduct(ProductCreateRequest request) {
//        productValidator.validateProductName(request.getProductName());
//
//        ProductCategory productCategory = productCategoryRepository.findById(request.getCategoryId())
//            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy loại sản phẩm với mã: " + request.getCategoryId()));
//
//        Supplier supplier = supplierRepository.findById(request.getSupplierId())
//            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhà cung cấp với mã: " + request.getSupplierId()));
//
//        Product sp = Product.builder()
//            .productName(request.getProductName())
//            .supplier(supplier)
//            .productCategory(productCategory)
//            .price(request.getPrice())
//            .build();
//
//        Product productSaved = productRepository.save(sp);
//        return ProductDTO.builder()
//            .productName(productSaved.getProductName())
//            .supplierName(productSaved.getSupplier().getSupplierName())
//            .categoryName(productSaved.getProductCategory().getCategoryName())
//            .price(productSaved.getPrice())
//            .build();
//    }

    @Transactional
    public ProductDTO createProduct(ProductCreateRequest request) {
        // Kiểm tra tên sản phẩm trùng lặp
        productValidator.validateProductName(request.getProductName());

        // Gọi stored procedure để thêm sản phẩm mới
        productRepository.createProduct(
            request.getCategoryId(),
            request.getSupplierId(),
            request.getProductName(),
            request.getPrice()
        );

        // Sử dụng flush và clear để đảm bảo thay đổi được đẩy vào cơ sở dữ liệu và làm mới cache
        entityManager.flush();
        entityManager.clear();

        // Lấy tên của category và supplier từ cơ sở dữ liệu
        String categoryName = productCategoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy loại sản phẩm với mã: " + request.getCategoryId()))
            .getCategoryName();

        String supplierName = supplierRepository.findById(request.getSupplierId())
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhà cung cấp với mã: " + request.getSupplierId()))
            .getSupplierName();

        // Tạo đối tượng ProductDTO để trả về
        return ProductDTO.builder()
            .productName(request.getProductName())
            .categoryName(categoryName)
            .supplierName(supplierName)
            .price(request.getPrice())
            .build();
    }



//    @Override
//    @Transactional
//    public ProductDTO updateProduct(Long id, ProductUpdateRequest request) {
//        Product existingProduct = productRepository.findByProductIdAndDeleted(id, false)
//                .orElseThrow(() -> new EntityNotExistedException("Không tồn tại sản phẩm với id: " + id));
//
//        productValidator.validateUpdateProductName(request.getProductName(), id);
//
//        if (request.getCategoryId() != null) {
//            ProductCategory productCategory = productCategoryRepository.findById(request.getCategoryId())
//                .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy loại sản phẩm với mã: " + request.getCategoryId()));
//            existingProduct.setProductCategory(productCategory);
//        }
//
//        if (request.getSupplierId() != null) {
//            Supplier supplier = supplierRepository.findById(request.getSupplierId())
//                .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhà cung cấp với mã: " + request.getSupplierId()));
//            existingProduct.setSupplier(supplier);
//        }
//
//        // Use MapStruct to update non-null fields from request
//        productMapper.updateProductFromRequest(request, existingProduct);
//
//        Product savedProduct = productRepository.save(existingProduct);
//        return productMapper.toProductDTO(savedProduct);
//    }

    @Transactional
    public ProductDTO updateProduct(Long productId, ProductUpdateRequest request) {
        // Kiểm tra sản phẩm có tồn tại không (sản phẩm bị xóa sẽ không thể cập nhật)
        Product existingProduct = productRepository.findByProductIdAndDeleted(productId, false)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại sản phẩm với id: " + productId));

        // Kiểm tra tên sản phẩm trùng lặp (ngoại trừ sản phẩm hiện tại)
        productValidator.validateUpdateProductName(request.getProductName(), productId);

        // Gọi stored procedure để cập nhật sản phẩm
        productRepository.updateProduct(
            productId,
            request.getCategoryId(),
            request.getSupplierId(),
            request.getProductName(),
            request.getPrice()
        );

        // Sử dụng flush để đảm bảo các thay đổi đã được đẩy vào cơ sở dữ liệu
        entityManager.flush();
        entityManager.clear(); // Xóa cache để lấy dữ liệu mới từ cơ sở dữ liệu

        // Lấy lại thông tin sản phẩm đã cập nhật
        Product updatedProduct = productRepository.findByProductIdAndDeleted(productId, false)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại sản phẩm với id: " + productId));

        // Chuyển đổi Product thành ProductDTO và trả về
        return productMapper.toProductDTO(updatedProduct);
    }





    @Override
    public void deleteProduct(Long id) {
        Product sp = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại sản phẩm với id: " + id));

        sp.setDeleted(true);
        productRepository.save(sp);
    }
}
