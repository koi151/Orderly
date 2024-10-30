package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.VaiTroCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.VaiTroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> xoaVaiTroQuanTri (@PathVariable(name = "id") Long id ) {
        vaiTroService.xoaVaiTroQuanTri(id);

        return new ResponseEntity<>(
            ResponseData.builder()
                .desc("Xóa thành công vai trò quản trị với id: " + id)
                .build()
            , HttpStatus.OK);
    }
}
