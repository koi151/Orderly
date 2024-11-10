package com.koi151.QTDL.entity.view;

import com.koi151.QTDL.entity.BaseEntity;
import com.koi151.QTDL.entity.BaseEntityNoDefaultVal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Entity
@Immutable
@Getter
@Table(name = "vw_product_details")
public class ProductDetailsView extends BaseEntity {

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    private BigDecimal price;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "supplier_name")
    private String supplierName;
}