package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Boolean existsBySupplierName(String ten);
}
