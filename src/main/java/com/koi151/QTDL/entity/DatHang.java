package com.koi151.QTDL.entity;

import com.koi151.QTDL.enums.TrangThaiDatHangEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity(name = "dat_hang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatHang extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDH")
    private long maDH;

    @ManyToOne
    @JoinColumn(name = "maDL", nullable = false)
    private DaiLy daiLy;

    @ManyToOne
    @JoinColumn(name = "maNV", nullable = false)
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "datHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CTDatHang> dsCTDatHang;

    @Column(name = "ghiChu", columnDefinition = "TEXT")
    private String ghiChu;

    @Enumerated(EnumType.STRING)
    @Column(name = "trangThai", nullable = false)
    private TrangThaiDatHangEnum trangThai;
}
