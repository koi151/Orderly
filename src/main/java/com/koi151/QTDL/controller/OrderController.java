package com.koi151.QTDL.controller;

import com.koi151.QTDL.mapper.ResponseDataMapper;
import com.koi151.QTDL.model.request.create.OrderCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ResponseDataMapper responseDataMapper;
    private static final int MAX_PAGE_SIZE = 20;

    @GetMapping("/")
    public ResponseEntity<ResponseData> findOrders (
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "10") int limit
    ) {
        int pageSize = Math.min(limit, MAX_PAGE_SIZE);
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        var pages = orderService.findOrders(pageable);

        ResponseData responseData = responseDataMapper.toResponseData(pages, page, pageSize);
        responseData.setDesc(pages.isEmpty()
            ? "Không tìm thấy đơn đặt hàng"
            : "Lấy dữ liệu các đơn đặt hàng thành công");

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseData> createOrder(
        @RequestBody @Valid OrderCreateRequest request
    ) {
        var accountCreated = orderService.createOrder(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo thành công đơn đặt hàng, mã đơn: " + accountCreated.getOrderId())
                .build()
            , HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteOrder (@PathVariable(name = "id") Long id ) {
        orderService.deleteOrder(id);

        return new ResponseEntity<>(
            ResponseData.builder()
                .desc("Xóa thành công đơn đặt hàng với id: " + id)
                .build()
            , HttpStatus.OK);
    }
}
