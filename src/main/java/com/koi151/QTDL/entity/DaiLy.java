package com.koi151.QTDL.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "daiLy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DaiLy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maDL;

    @Column(name = "tenDL", nullable = false, length = 100)
    private String tenDL;

    @Column(name = "diaChi", length = 100)
    private String diaCHi;

    @Column(name = "sdt", length = 20)
    private String sdt;

    @Column(name = "nguoiDaiDien", length = 100)
    private String nguoiDaiDien;
}
