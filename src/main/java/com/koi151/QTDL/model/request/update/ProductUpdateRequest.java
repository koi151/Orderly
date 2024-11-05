package com.koi151.QTDL.model.request.update;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateRequest {
    private Long categoryId;
    private Long supplierId;

    @Size(max = 100, message = "Tên sản phẩm không quá {max} kí tự")
    private String productName;

    @PositiveOrZero(message = "Giá không hợp lệ")
    @DecimalMax(value = "1000000000", message = "Giá không vượt quá {value}")
    private BigDecimal price;
}
