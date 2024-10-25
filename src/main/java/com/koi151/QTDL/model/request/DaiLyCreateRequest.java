package com.koi151.QTDL.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DaiLyCreateRequest {

    @Size(max = 100, message = "Tên đại lý không vượt quá {max} kí tự")
    private String tenDL;

    @Size(max = 100, message = "Thông tin địa chỉ không vượt quá {max} kí tự")
    private String diaChi;

    @Size(max = 20, message = "Số điện thoại không hợp lệ")
    private String sdt;

    @Column(name = "nguoiDaiDien", length = 100)
    @Size(max = 100, message = "Thông tin người đại diện không vượt quá {max} kí tự")
    private String nguoiDaiDien;
}
