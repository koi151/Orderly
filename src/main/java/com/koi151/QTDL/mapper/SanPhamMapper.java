package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.SanPham;
import com.koi151.QTDL.model.dto.SanPhamCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SanPhamMapper {

    SanPhamCreateDTO toSanPhamCreateDTO(SanPham sp);
}
