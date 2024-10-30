package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.DatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatHangRepository extends JpaRepository<DatHang, Long> {
}
