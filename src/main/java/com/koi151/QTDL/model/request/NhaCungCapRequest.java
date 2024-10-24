package com.koi151.QTDL.model.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhaCungCapRequest {

    @Size(max = 100, message = "Tên nhà cung cấp không vượt quá {max} kí tự")
    private String tenNCC;

    @Size(max = 100, message = "Địa chỉ nhà cung cấp không vượt quá {max} kí tự")
    private String diaChi;

    @Size(max = 65535, message = "Thông tin người đại diện không vượt quá {max} kí tự")
    private String TTNguoiDaiDien;
}
