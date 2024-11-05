package com.koi151.QTDL.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "agency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Agency extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long agencyId;

    @Column(name = "agencyName", nullable = false, length = 100)
    String agencyName;

    @Column(name = "address", length = 100)
    String address;

    @Column(name = "phone", length = 20)
    String phone;

    @Column(name = "repInfo", length = 100)
    String repInfo;
}
