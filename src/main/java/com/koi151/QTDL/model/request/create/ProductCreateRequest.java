package com.koi151.QTDL.model.request.create;


import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreateRequest {

    @NotNull(message = "Sản phẩm cần có mã loại")
    private Long categoryId;

    @NotNull(message = "Sản phẩm cần có mã nhà cung cấp")
    private Long supplierId;

    @NotBlank(message = "Thiếu tên sản phẩm")
    @Size(max = 100, message = "Tên sản phẩm không quá {max} kí tự")
    private String productName;

    @PositiveOrZero(message = "Giá không hợp lệ")
    @DecimalMax(value = "1000000000", message = "Giá không vượt quá {value}")
    private BigDecimal price;
}
