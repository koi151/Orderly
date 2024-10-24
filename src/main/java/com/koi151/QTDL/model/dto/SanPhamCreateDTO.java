package com.koi151.QTDL.model.dto;

import com.koi151.QTDL.entity.LoaiSanPham;
import com.koi151.QTDL.entity.NhaCungCap;
import jakarta.persistence.*;

import java.math.BigDecimal;


public class SanPhamCreateDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maSP;

//    private List<CTDatHang> dsCTDatHang;
    private LoaiSanPham loaiSanPham;
    private NhaCungCap nhaCungCap;
    private String tenSP;
    private BigDecimal gia;
}
