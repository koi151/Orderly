package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.VaiTro;
import com.koi151.QTDL.model.dto.VaiTroCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VaiTroMapper {

    VaiTroCreateDTO toVaiTroDTO(VaiTro vt);
}
