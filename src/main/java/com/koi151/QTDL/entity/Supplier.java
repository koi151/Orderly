package com.koi151.QTDL.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "supplier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long supplierId;

    @OneToMany(mappedBy = "supplier",
        cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Product> products;

    @Column(name = "supplierName", nullable = false, length = 100)
    private String supplierName;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "repInfo", columnDefinition = "TEXT")
    private String repInfo;
}
