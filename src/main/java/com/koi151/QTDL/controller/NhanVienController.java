package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.NhanVienRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.NhanVienService;
import com.koi151.QTDL.service.impl.NhanVienServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        @RequestBody @Valid NhanVienRequest request
    ) {
        var accountCreated = nhanVienService.taoTaiKhoan(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tao thanh cong tai khoan nhan vien")
                .build()
            , HttpStatus.CREATED);
    }
}

