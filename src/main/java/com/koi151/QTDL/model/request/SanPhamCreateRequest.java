package com.koi151.QTDL.model.request;


import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPhamCreateRequest {

    @NotNull(message = "Sản phẩm cần có mã loại")
    private Long maLoai;

    @NotNull(message = "Sản phẩm cần có mã nhà cung cấp")
    private Long maNCC;

    @Size(max = 100, message = "Tên sản phẩm không quá {max} kí tự")
    private String tenSP;

    @PositiveOrZero(message = "Giá không được âm")
    @DecimalMax(value = "1000000000", message = "Giá không vượt quá {value}")
    private BigDecimal gia;
}
