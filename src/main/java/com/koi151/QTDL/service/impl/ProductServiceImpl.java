package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.Product;
import com.koi151.QTDL.entity.view.ProductDetailsView;
import com.koi151.QTDL.mapper.ProductMapper;
import com.koi151.QTDL.model.dto.ProductDTO;
import com.koi151.QTDL.model.dto.ProductDTODetails;
import com.koi151.QTDL.model.request.create.ProductCreateRequest;
import com.koi151.QTDL.model.request.search.ProductSearchRequest;
import com.koi151.QTDL.model.request.update.ProductUpdateRequest;
import com.koi151.QTDL.repository.ProductCategoryRepository;
import com.koi151.QTDL.repository.SupplierRepository;
import com.koi151.QTDL.repository.ProductRepository;
import com.koi151.QTDL.repository.view.ProductDetailsViewRepository;
import com.koi151.QTDL.service.ProductService;
import com.koi151.QTDL.specification.ProductDetailsSpecification;
import com.koi151.QTDL.validator.ProductValidator;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductDetailsViewRepository productDetailsViewRepository;

    private final SupplierRepository supplierRepository;
    private final ProductValidator productValidator;
    private final EntityManager entityManager;
    private static final String DEFAULT_SORT_FIELD = "createdAt"; // Tên thuộc tính camelCase trong Entity

    // SORT ======================
    @Override
    public Page<ProductDTODetails> getProducts(ProductSearchRequest request, Pageable pageable) {
        // Xây dựng Specification dựa trên yêu cầu tìm kiếm
        Specification<ProductDetailsView> spec = Specification.where(
            ProductDetailsSpecification.hasProductId(request.getProductId())
            .and(ProductDetailsSpecification.hasProductNameLike(request.getProductName()))
            .and(ProductDetailsSpecification.hasCategoryNameLike(request.getCategoryName()))
            .and(ProductDetailsSpecification.hasSupplierNameLike(request.getSupplierName()))
            .and(ProductDetailsSpecification.hasPriceEqualTo(request.getPrice()))
            .and(ProductDetailsSpecification.hasPriceGreaterThanOrEqualTo(request.getMinPrice()))
            .and(ProductDetailsSpecification.hasPriceLessThanOrEqualTo(request.getMaxPrice()))
        );

        // Kiểm tra và xác thực sortField để ngăn chặn SQL Injection
        String sortField = request.getSortField();
        if (!isValidSortField(sortField)) {
            sortField = DEFAULT_SORT_FIELD; // Sử dụng sortField mặc định nếu không hợp lệ
        }

        // Áp dụng sắp xếp
        Sort sort = Sort.by(
            "DESC".equalsIgnoreCase(request.getSortDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC,
            sortField
        );

        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        // Thực hiện truy vấn với Specification và Pageable đã cấu hình
        Page<ProductDetailsView> retrievedProducts = productDetailsViewRepository.findAll(spec, sortedPageable);

        // Chuyển đổi Page<ProductDetailsView> sang Page<ProductDetailsDTO>
        return retrievedProducts.map(product -> ProductDTODetails.builder()
            .productId(product.getProductId())
            .productName(product.getProductName())
            .price(product.getPrice())
            .categoryName(product.getCategoryName())
            .supplierName(product.getSupplierName())
            .createdAt(product.getCreatedAt())
            .build());
    }

    private boolean isValidSortField(String sortField) {
        return sortField != null && (
            sortField.equals("createdAt") ||
                sortField.equals("productName") ||
                sortField.equals("price")
        );
    }


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

    @Override
    public ProductDTO createProduct(ProductCreateRequest request) {         // Gọi stored procedure để thêm sản phẩm mới
        productRepository.createProduct(
            request.getCategoryId(),
            request.getSupplierId(),
            request.getProductName(),
            request.getPrice()
        );

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

    @Override
    @Transactional
    public ProductDTO updateProduct(Long productId, ProductUpdateRequest request) {
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

        entityManager.flush();
        entityManager.clear();

        // Lấy lại thông tin sản phẩm đã cập nhật từ cơ sở dữ liệu
        Product updatedProduct = productRepository.findByProductIdAndDeleted(productId, false)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại sản phẩm với id: " + productId));

        return ProductDTO.builder()
            .categoryName(updatedProduct.getProductCategory().getCategoryName())
            .supplierName(updatedProduct.getSupplier().getSupplierName())
            .productName(updatedProduct.getProductName())
            .price(updatedProduct.getPrice())
            .build();
    }

    //
    @Override
    public void deleteProduct(Long id) {
        Product sp = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại sản phẩm với id: " + id));

        sp.setDeleted(true);
        productRepository.save(sp);
    }
}
