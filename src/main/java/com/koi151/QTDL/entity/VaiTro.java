package com.koi151.QTDL.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "vaiTro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaiTro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maVT;

    @OneToMany(mappedBy = "vaiTro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NhanVien> dsNhanVien;

    @Column(name = "tenVT", nullable = false, length = 50)
    private String tenVT;

    @Column(name = "moTa", columnDefinition = "TEXT")
    private String moTa;
}
