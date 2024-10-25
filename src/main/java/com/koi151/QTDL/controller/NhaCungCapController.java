package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.NhaCungCapCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.NhaCungCapService;
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
@RequestMapping("/nha-cung-cap")
public class NhaCungCapController {

    private final NhaCungCapService nhaCungCapService;

    @PostMapping("/")
    public ResponseEntity<ResponseData> taoNhaCungCap(
        @RequestBody @NotNull @Valid NhaCungCapCreateRequest request
    ) {
        var accountCreated = nhaCungCapService.taoNhaCungCap(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo thành công nhà cung cấp " + request.getTenNCC())
                .build()
            , HttpStatus.CREATED);
    }
}
