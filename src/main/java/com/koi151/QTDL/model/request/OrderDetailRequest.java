package com.koi151.QTDL.model.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailRequest {

    @NotNull(message = "Mã sản phẩm không được trống")
    private Long productId;

    @NotNull(message = "Số lượng sản phẩm không được trống")
    @Min(value = 0, message = "Số lượng sản phẩm phải lớn hơn 0")
    private Integer quantity;

    LocalDateTime deliveryDate;
}
