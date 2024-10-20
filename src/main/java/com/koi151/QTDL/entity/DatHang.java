package com.koi151.QTDL.entity;

import com.koi151.QTDL.enums.TrangThaiDatHang;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity(name = "datHang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDH")
    private long maDH;

    @ManyToOne
    @JoinColumn(name = "maDL", nullable = false)  // Khóa ngoại tham chiếu tới DaiLy
    private DaiLy daiLy;

    @ManyToOne
    @JoinColumn(name = "maNV", nullable = false)  // Khóa ngoại tham chiếu tới NhanVien
    private NhanVien nhanVien;

    @Column(name = "ghiChu", columnDefinition = "TEXT")
    private String ghiChu;

    @Enumerated(EnumType.STRING)
    @Column(name = "trangThai", nullable = false)
    private TrangThaiDatHang trangThai;

    @OneToMany(mappedBy = "datHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CTDatHang> dsCTDatHang;
}
