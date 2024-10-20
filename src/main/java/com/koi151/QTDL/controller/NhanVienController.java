package com.koi151.QTDL.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nhan-vien")
public class NhanVienController {

    @GetMapping("/")
    public ResponseEntity<String> layNhanVien() {
        return ResponseEntity.ok("OK");
    }
}
