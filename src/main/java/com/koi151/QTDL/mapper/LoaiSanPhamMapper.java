package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.LoaiSanPham;
import com.koi151.QTDL.model.dto.LoaiSanPhamCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoaiSanPhamMapper {

    LoaiSanPhamCreateDTO toLoaiSanPhamDTO(LoaiSanPham loaiSP);
}
