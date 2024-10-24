package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {

    List<NhanVien> getAllByDaXoa(Boolean deleted);

    Boolean existsByEmailOrSdt(String email, String sdt);
}
