package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    @Procedure(procedureName = "createAgency")
    Long createAgency(
        @Param("p_agency_name") String agencyName,
        @Param("p_phone") String phone,
        @Param("p_address") String address,
        @Param("p_rep_info") String repInfo
    );

    @Procedure(procedureName = "updateAgency")
    void updateAgency(
        @Param("p_agency_id") Long agencyId,
        @Param("p_agency_name") String agencyName,
        @Param("p_phone") String phone,
        @Param("p_address") String address,
        @Param("p_rep_info") String repInfo
    );
    Boolean existsByAgencyName(String name);
    Boolean existsByAgencyNameAndAgencyIdNot(String name, Long id);
}
