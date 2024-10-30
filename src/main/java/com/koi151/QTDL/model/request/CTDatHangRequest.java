package com.koi151.QTDL.model.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CTDatHangRequest {

    @NotNull(message = "Mã sản phẩm không được trống")
    private Long maSP;

    @NotNull(message = "Số lượng sản phẩm không được trống")
    @Min(value = 0, message = "Số lượng sản phẩm phải lớn hơn 0")
    private Integer soLg;

    LocalDateTime ngayGiaoHang;

//    @DecimalMax(value = "1000000000", message = "Giá không vượt quá {value}")
//    private BigDecimal donGia;
}
