package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.NhanVienRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.NhanVienService;
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

//    @PostMapping("/")
//    public ResponseEntity<ResponseData> taoTaiKhoan(
//        @RequestBody @Valid NhanVienRequest request
////        @RequestPart(required = false) MultipartFile avatar
//    ) {
//        var accountCreated = nhanVienService.taoTaiKhoan(request);
//        return new ResponseEntity<>(
//            ResponseData.builder()
//                .data(accountCreated)
//                .desc("Tao thanh cong tai khoan nhan vien")
//                .build()
//            , HttpStatus.CREATED);
//    }
}
