package com.koi151.QTDL.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "nha_cung_cap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhaCungCap extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maNCC;

    @OneToMany(mappedBy = "nhaCungCap",
        cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<SanPham> dsSanPham;

    @Column(name = "tenNCC", nullable = false, length = 100)
    private String tenNCC;

    @Column(name = "diaChi", nullable = false, length = 100)
    private String diaChi;

    @Column(name = "TTNguoiDaiDien", columnDefinition = "TEXT")
    private String TTNguoiDaiDien;
}
