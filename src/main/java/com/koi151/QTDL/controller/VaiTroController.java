package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.VaiTroCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.VaiTroService;
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
@RequestMapping("/vai-tro")
public class VaiTroController {

    private final VaiTroService vaiTroService;

    @PostMapping("/")
    public ResponseEntity<ResponseData> taoVaiTro(
        @RequestBody @Valid VaiTroCreateRequest request
    ) {
        var accountCreated = vaiTroService.taoVaiTro(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo thành công vai trò " + request.getTenVT())
                .build()
            , HttpStatus.CREATED);
    }
}
