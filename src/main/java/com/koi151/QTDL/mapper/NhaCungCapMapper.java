package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.NhaCungCap;
import com.koi151.QTDL.model.dto.NhaCungCapCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NhaCungCapMapper {
    NhaCungCapCreateDTO toNhaCungCapCreateDTO(NhaCungCap entity);
}
