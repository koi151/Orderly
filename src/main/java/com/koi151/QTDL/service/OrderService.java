package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.OrderCreateDTO;
import com.koi151.QTDL.model.request.OrderCreateRequest;

public interface OrderService {
    OrderCreateDTO createOrder(OrderCreateRequest request);
    void deleteOrder(Long id);
}
