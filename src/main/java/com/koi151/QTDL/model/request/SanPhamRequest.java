package com.koi151.QTDL.model.request;


import com.koi151.QTDL.entity.LoaiSanPham;
import com.koi151.QTDL.entity.NhaCungCap;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPhamRequest {

    @NotBlank(message = "San pham can co ma loai")
    private Long maLoai;

    @NotBlank(message = "San pham can co ma nha cung cap")
    private Long maNCC;

    @Size(max = 100, message = "Ten san pham khong vuot qua {max} ki tu")
    private String tenSP;

    @PositiveOrZero(message = "Gia khong duoc am")
    @DecimalMax(value = "1000000000", message = "Gia khong vuot qua {value}")
    private BigDecimal gia;
}
