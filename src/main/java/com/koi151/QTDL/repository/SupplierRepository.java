package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Boolean existsBySupplierName(String name);

    Supplier findBySupplierIdAndDeleted(Long id, Boolean deleted);

    @Query("SELECT COUNT(p) from product p WHERE p.supplier.supplierId = :supplierId")
    Long countProductsBySupplierId(@Param("supplierId") Long supplierId);
}
