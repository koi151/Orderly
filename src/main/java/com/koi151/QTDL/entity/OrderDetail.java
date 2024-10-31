package com.koi151.QTDL.entity;

import com.koi151.QTDL.entity.keys.OrderDetailKey;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "order_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {

    @EmbeddedId
    private OrderDetailKey orderDetailKey;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", nullable = false)
    private Order order;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "deliveryDate", columnDefinition = "TIMESTAMP")
    private LocalDateTime deliveryDate;

//    @Column(name = "donGia", precision = 10)
//    private BigDecimal donGia;
}
