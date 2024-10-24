package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, Long> {

}
