package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.ProductCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

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
