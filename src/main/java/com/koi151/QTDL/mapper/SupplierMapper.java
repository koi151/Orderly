package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.Supplier;
import com.koi151.QTDL.model.dto.SupplierCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierMapper {
    SupplierCreateDTO toSupplierCreateDTO(Supplier entity);
}
