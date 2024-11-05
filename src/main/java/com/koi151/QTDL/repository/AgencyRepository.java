package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    Boolean existsByAgencyName(String name);
    Boolean existsByAgencyNameAndAgencyIdNot(String name, Long id);
}
