package com.koi151.QTDL.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NhaCungCapCreateDTO {
    private Long maNCC;
    private String tenNCC;
    private String diaChi;
    private String TTNguoiDaiDien;
}
