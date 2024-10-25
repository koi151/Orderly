package com.koi151.QTDL.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SanPhamCreateDTO {
    private String tenLoaiSP;
    private String tenNCC;
    private String tenSP;
    private BigDecimal gia;
}
