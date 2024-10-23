package com.koi151.QTDL.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "nhan_vien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhanVien extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maNV;

    @ManyToOne
    @JoinColumn(name = "maVT")
    private VaiTro vaiTro;

    @Column(name = "hoTen", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "sdt", length = 20)
    private String sdt;

    @Column(name = "matKhau", length = 100)
    private String matKhau;
}
