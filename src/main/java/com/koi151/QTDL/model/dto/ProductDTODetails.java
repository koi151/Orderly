package com.koi151.QTDL.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTODetails {
    private Long productId;
    private String productName;
    private BigDecimal price;
    private String categoryName;
    private String supplierName;
    private LocalDateTime createdAt;
}
