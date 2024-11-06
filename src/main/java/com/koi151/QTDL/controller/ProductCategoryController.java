package com.koi151.QTDL.controller;

import com.koi151.QTDL.mapper.ResponseDataMapper;
import com.koi151.QTDL.model.request.create.ProductCategoryCreateRequest;
import com.koi151.QTDL.model.request.search.ProductCategorySearchRequest;
import com.koi151.QTDL.model.request.update.ProductCategoryUpdateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;
    private final ResponseDataMapper responseDataMapper;

    private static final int MAX_PAGE_SIZE = 20;

    @GetMapping("/")
    public ResponseEntity<ResponseData> findProperties (
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "10") int limit,
        @RequestBody(required = false) @Valid ProductCategorySearchRequest request)
    {
        int pageSize = Math.min(limit, MAX_PAGE_SIZE);
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        if (request == null) {
            request = new ProductCategorySearchRequest();
        }

        var propertiesPage = productCategoryService.getCategories(request, pageable);

        ResponseData responseData = responseDataMapper.toResponseData(propertiesPage, page, pageSize);
        responseData.setDesc(propertiesPage.isEmpty()
            ? "No property found"
            : "Get properties succeed");

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseData> createCategory(
        @RequestBody @Valid ProductCategoryCreateRequest request
    ) {
        var accountCreated = productCategoryService.createCategory(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo thành công loại sản phẩm: " + request.getCategoryName())
                .build()
            , HttpStatus.CREATED);
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<ResponseData> updateProductCategory(@PathVariable Long categoryId,
                                          @Valid @RequestBody ProductCategoryUpdateRequest request) {
        var updatedCategory = productCategoryService.updateCategory(categoryId, request);
        return ResponseEntity.ok(ResponseData.builder()
            .data(updatedCategory)
            .desc("Cập nhật thành công thông tin danh mục sản phẩm " + updatedCategory.getCategoryName())
            .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteCategory (@PathVariable(name = "id") Long id ) {
        productCategoryService.deleteCategory(id);

        return new ResponseEntity<>(
            ResponseData.builder()
                .desc("Xóa thành công loại sản phẩm với id: " + id)
                .build()
            , HttpStatus.OK);
    }
}
