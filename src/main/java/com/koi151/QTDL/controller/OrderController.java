package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.OrderCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

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
