package com.koi151.QTDL.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatHangCreateDTO {
    private long maDH;
    private String tenDL;
    private String tenNV;
    private String sdtNV;
    private String ghiChu;
    private String trangThai;
}
