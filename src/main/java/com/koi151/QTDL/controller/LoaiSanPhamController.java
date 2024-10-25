package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.LoaiSanPhamCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.LoaiSanPhamService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loai-san-pham")
public class LoaiSanPhamController {

    private final LoaiSanPhamService loaiSanPhamService;

    @PostMapping("/")
    public ResponseEntity<ResponseData> taoTaiKhoan(
        @RequestBody @NotNull @Valid LoaiSanPhamCreateRequest request
    ) {
        var accountCreated = loaiSanPhamService.taoLoaiSP(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tao thanh cong loai tai khoan: " + request.getTenLoai())
                .build()
            , HttpStatus.CREATED);
    }
}
