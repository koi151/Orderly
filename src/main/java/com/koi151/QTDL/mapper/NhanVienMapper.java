package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.NhanVien;
import com.koi151.QTDL.model.dto.NhanVienDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NhanVienMapper {

    NhanVienDTO toNhanVienDTO(NhanVien nv);
}
