package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.LoaiSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Long> {
    Boolean existsByTenLoai(String name);
}
