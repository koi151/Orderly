package com.koi151.QTDL.controller;

import com.koi151.QTDL.mapper.ResponseDataMapper;
import com.koi151.QTDL.model.dto.ProductStatisticsDTO;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.ProductStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/statistics")
public class ProductStatisticsController {

    private final ProductStatisticsService productStatisticsService;
//    private final ResponseDataMapper responseDataMapper;

    @GetMapping("/")
    public ResponseEntity<ResponseData> getProductStatistics() {
        ProductStatisticsDTO statistics = productStatisticsService.getProductStatistics();

        ResponseData responseData = ResponseData.builder()
            .data(statistics)
            .desc("Thống kê sản phẩm thành công")
            .build();

        return ResponseEntity.ok(responseData);
    }
}