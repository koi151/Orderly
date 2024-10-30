package com.koi151.QTDL.entity;

import com.koi151.QTDL.entity.keys.CTDatHangKey;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "ct_dat_hang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CTDatHang {

    @EmbeddedId
    private CTDatHangKey ctDatHangKey;

    @ManyToOne
    @MapsId("maSP")
    @JoinColumn(name = "maSP", referencedColumnName = "maSP", nullable = false)
    private SanPham sanPham;

    @ManyToOne
    @MapsId("maDH")
    @JoinColumn(name = "maDH", referencedColumnName = "maDH", nullable = false)
    private DatHang datHang;

    @Column(name = "soLg", nullable = false)
    private int soLg;

    @Column(name = "ngayGiaoHang", columnDefinition = "TIMESTAMP")
    private LocalDateTime ngayGiaoHang;

//    @Column(name = "donGia", precision = 10)
//    private BigDecimal donGia;
}
