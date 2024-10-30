package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.NhanVienCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.NhanVienService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nhan-vien")
public class NhanVienController {

    private final NhanVienService nhanVienService;

    @GetMapping("/")
    public ResponseEntity<String> layNhanVien() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/")
    public ResponseEntity<ResponseData> taoTaiKhoan(
        @RequestBody @NotNull @Valid NhanVienCreateRequest request
    ) {
        var accountCreated = nhanVienService.taoTaiKhoan(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo thành công tài khoản nhân viên")
                .build()
            , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> xoaNhanVien (@PathVariable(name = "id") Long id ) {
        nhanVienService.xoaNhanVien(id);

        return new ResponseEntity<>(
            ResponseData.builder()
                .desc("Xóa thành công nhân viên với id: " + id)
                .build()
            , HttpStatus.OK);
    }
}

