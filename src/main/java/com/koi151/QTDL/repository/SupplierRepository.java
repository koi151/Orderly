package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Page<Supplier> findAllByDeleted(Boolean deleted, Pageable pageable);

    @Procedure(procedureName = "createSupplier")
    Long createSupplier(
        @Param("p_supplier_name") String supplierName,
        @Param("p_address") String address,
        @Param("p_rep_info") String repInfo
    );

    @Procedure(procedureName = "updateSupplier")
    void updateSupplier(
        @Param("p_supplier_id") Long supplierId,
        @Param("p_supplier_name") String supplierName,
        @Param("p_address") String address,
        @Param("p_rep_info") String repInfo
    );

    Boolean existsBySupplierName(String name);

    Supplier findBySupplierIdAndDeleted(Long id, Boolean deleted);

    @Query("SELECT COUNT(p) from product p WHERE p.supplier.supplierId = :supplierId")
    Long countProductsBySupplierId(@Param("supplierId") Long supplierId);
}
