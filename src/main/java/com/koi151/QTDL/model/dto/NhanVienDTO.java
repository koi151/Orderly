package com.koi151.QTDL.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NhanVienDTO {
    private long maNV;
    private String hoTen;
    private String email;
    private String sdt;
}
