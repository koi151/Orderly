package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    Boolean existsByTenSP(String ten);
}
