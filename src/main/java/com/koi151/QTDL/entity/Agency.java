package com.koi151.QTDL.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "agency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agency extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long agencyId;

    @Column(name = "agencyName", nullable = false, length = 100)
    private String agencyName;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "repInfo", length = 100)
    private String repInfo;
}
