package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.SanPhamCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.SanPhamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/san-pham")
public class SanPhamController {

    private final SanPhamService sanPhamService;

    @PostMapping("/")
    public ResponseEntity<ResponseData> taoSanPham(
        @RequestBody @Valid SanPhamCreateRequest request
    ) {
        var accountCreated = sanPhamService.taoSanPham(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo sản phẩm thành công")
                .build()
            , HttpStatus.CREATED);
    }
}
