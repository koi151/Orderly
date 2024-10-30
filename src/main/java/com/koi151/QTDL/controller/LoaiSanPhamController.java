package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.LoaiSanPhamCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.LoaiSanPhamService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loai-san-pham")
public class LoaiSanPhamController {

    private final LoaiSanPhamService loaiSanPhamService;

    @PostMapping("/")
    public ResponseEntity<ResponseData> taoLoaiSP(
        @RequestBody @NotNull @Valid LoaiSanPhamCreateRequest request
    ) {
        var accountCreated = loaiSanPhamService.taoLoaiSP(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo thành công loại sản phẩm: " + request.getTenLoai())
                .build()
            , HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> xoaLoaiSP (@PathVariable(name = "id") Long id ) {
        loaiSanPhamService.xoaLoaiSP(id);

        return new ResponseEntity<>(
            ResponseData.builder()
                .desc("Xóa thành công loại sản phẩm với id: " + id)
                .build()
            , HttpStatus.OK);
    }
}
