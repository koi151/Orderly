package com.koi151.QTDL.entity;

import com.koi151.QTDL.entity.keys.CTDatHangKey;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "CTDatHang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CTDatHang {

    @EmbeddedId
    private CTDatHangKey ctDatHangKey;

    @ManyToOne
    @MapsId("maSP")  // Ánh xạ khóa chính maSP
    @JoinColumn(name = "maSP", referencedColumnName = "maSP", nullable = false)
    private SanPham sanPham;  // Khóa ngoại tới SanPham

    @ManyToOne
    @MapsId("maDH")  // Ánh xạ khóa chính maDH
    @JoinColumn(name = "maDH", referencedColumnName = "maDH", nullable = false)
    private DatHang datHang;  // Khóa ngoại tới DatHang

    @Column(name = "soLg", nullable = false)
    private int soLg;

    @Column(name = "ngayGiaoHang", columnDefinition = "TIMESTAMP")
    private LocalDateTime ngayGiaoHang;

    @Column(name = "donGia", precision = 10)
    private BigDecimal donGia;
}
