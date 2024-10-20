package com.koi151.QTDL.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "sanPham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maSP;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CTDatHang> dsCTDatHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maLoai")
    @NotNull(message = "Thieu ma loai san pham")
    private LoaiSanPham loaiSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNCC")
    private NhaCungCap nhaCungCap;

    @Column(name = "tenSP", nullable = false, length = 100)
    @NotBlank(message = "Thieu ten san pham")
    @Size(max = 100, message = "Ma san pham khong vuot qua {max} ki tu")
    private String tenSP;

    @Column(name = "gia", precision = 10)
    @NotNull(message = "Post service pricing cannot be empty")
    @PositiveOrZero(message = "Post service pricing must be non-negative value")
    @DecimalMax(value = "1000000000", message = "Post service pricing cannot exceed {value}")
    private BigDecimal gia;
}
