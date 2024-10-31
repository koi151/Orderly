package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.ProductCategoryCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.ProductCategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @PostMapping("/")
    public ResponseEntity<ResponseData> createCategory(
        @RequestBody @NotNull @Valid ProductCategoryCreateRequest request
    ) {
        var accountCreated = productCategoryService.createCategory(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo thành công loại sản phẩm: " + request.getCategoryName())
                .build()
            , HttpStatus.CREATED);
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
