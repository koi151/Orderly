package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.DaiLyCreateRequest;
import com.koi151.QTDL.model.request.DatHangCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.DaiLyService;
import com.koi151.QTDL.service.DatHangService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dat-hang")
public class DatHangController {

    private final DatHangService datHangService;

    @PostMapping("/")
    public ResponseEntity<ResponseData> taoDatHang(
        @RequestBody @NotNull @Valid DatHangCreateRequest request
    ) {
        var accountCreated = datHangService.taoDatHang(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo thành công đơn đặt hàng, mã đơn: " + accountCreated.getMaDH())
                .build()
            , HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> xoaDonDatHang (@PathVariable(name = "id") Long id ) {
        datHangService.xoaDatHang(id);

        return new ResponseEntity<>(
            ResponseData.builder()
                .desc("Xóa thành công đơn đặt hàng với id: " + id)
                .build()
            , HttpStatus.OK);
    }
}
