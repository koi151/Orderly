package com.koi151.QTDL.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DaiLyCreateDTO {
    private long maDL;
    private String tenDL;
    private String diaChi;
    private String sdt;
    private String nguoiDaiDien;
}
