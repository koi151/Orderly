package com.koi151.QTDL.controller;

import com.koi151.QTDL.entity.Product;
import com.koi151.QTDL.mapper.ResponseDataMapper;
import com.koi151.QTDL.model.dto.ProductDTODetails;
import com.koi151.QTDL.model.request.create.ProductCreateRequest;
import com.koi151.QTDL.model.request.search.ProductCategorySearchRequest;
import com.koi151.QTDL.model.request.search.ProductSearchRequest;
import com.koi151.QTDL.model.request.update.ProductUpdateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ResponseDataMapper responseDataMapper;

    private static final int MAX_PAGE_SIZE = 20;


//    @GetMapping("/")
//    public ResponseEntity<ResponseData> findCategories (
//        @RequestParam(required = false, defaultValue = "1") int page,
//        @RequestParam(required = false, defaultValue = "10") int limit,
//        @RequestBody(required = false) @Valid ProductCategorySearchRequest request)
//    {
//        int pageSize = Math.min(limit, MAX_PAGE_SIZE);
//        Pageable pageable = PageRequest.of(page - 1, pageSize);
//
//        if (request == null) {
//            request = new ProductCategorySearchRequest();
//        }
//
//        var propertiesPage = productCategoryService.getCategories(request, pageable);
//
//        ResponseData responseData = responseDataMapper.toResponseData(propertiesPage, page, pageSize);
//        responseData.setDesc(propertiesPage.isEmpty()
//            ? "No property found"
//            : "Get properties succeed");
//
//        return ResponseEntity.ok(responseData);
//    }

    @GetMapping("/")
    public ResponseEntity<ResponseData> findProducts (
        @RequestParam(required = false, defaultValue = "1")
        @Min(value = 1, message = "Page number must be at least 1") int page,
        @RequestParam(required = false, defaultValue = "10")
        @Min(value = 1, message = "Page size must be at least 1") int limit,
        @Valid @ModelAttribute ProductSearchRequest request
    )
    {
        // Giới hạn kích thước trang tối đa để ngăn ngừa lạm dụng
        int pageSize = Math.min(limit, MAX_PAGE_SIZE);
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        // Đảm bảo request không null
        if (request == null) {
            request = new ProductSearchRequest();
        }

        // Lấy Page<ProductDTODetails> từ Service
        Page<ProductDTODetails> productsPage = productService.getProducts(request, pageable);

        // Đóng gói vào ResponseData
        ResponseData responseData = responseDataMapper.toResponseData(productsPage, page, pageSize);
        responseData.setDesc(productsPage.isEmpty()
            ? "Không tìm thấy sản phẩm"
            : "Tìm kiếm sản phẩm thành công");

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseData> createProduct(
        @RequestBody @Valid ProductCreateRequest request
    ) {
        var accountCreated = productService.createProduct(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo sản phẩm thành công")
                .build()
            , HttpStatus.CREATED);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ResponseData> updateProduct(@PathVariable Long productId,
                                                      @Valid @RequestBody ProductUpdateRequest request) {
        var updatedProduct = productService.updateProduct(productId, request);
        return ResponseEntity.ok(ResponseData.builder()
            .data(updatedProduct)
            .desc("Cập nhật thành công thông tin danh mục sản phẩm " + updatedProduct.getProductName())
            .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteProduct (@PathVariable(name = "id") Long id ) {
        productService.deleteProduct(id);

        return new ResponseEntity<>(
            ResponseData.builder()
                .desc("Xóa thành công sản phẩm với id: " + id)
                .build()
            , HttpStatus.OK);
    }
}
