package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.OrderDetailsDTO;
import com.koi151.QTDL.model.request.create.OrderCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderDetailsDTO> findOrders(Pageable pageable);
    OrderDetailsDTO createOrder(OrderCreateRequest request);
    void deleteOrder(Long id);
}
