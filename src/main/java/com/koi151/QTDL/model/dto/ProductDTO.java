package com.koi151.QTDL.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDTO {
    private String categoryName;
    private String supplierName;
    private String productName;
    private BigDecimal price;
}
