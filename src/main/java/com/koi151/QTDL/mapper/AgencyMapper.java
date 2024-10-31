package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.Agency;
import com.koi151.QTDL.model.dto.AgencyCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AgencyMapper {

    AgencyCreateDTO toAgencyCreateDTO(Agency agency);
}
