package com.koi151.QTDL.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "san_pham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SanPham extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maSP;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CTDatHang> dsCTDatHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maLoai")
    private LoaiSanPham loaiSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNCC")
    private NhaCungCap nhaCungCap;

    @Column(name = "tenSP", nullable = false, length = 100)
    private String tenSP;

    @Column(name = "gia", precision = 10)
    @PositiveOrZero(message = "Gia khong duoc am")
    private BigDecimal gia;
}
