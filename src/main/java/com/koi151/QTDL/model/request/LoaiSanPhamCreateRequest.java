package com.koi151.QTDL.model.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoaiSanPhamCreateRequest {

    @Size(max = 100, message = "Ten loai san pham khong duoc qua {max} ki tu")
    private String tenLoai;
}
