package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.Supplier;
import com.koi151.QTDL.model.dto.SupplierDTO;
import com.koi151.QTDL.model.request.SupplierRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierMapper {
    SupplierDTO toSupplierCreateDTO(Supplier entity);
    Supplier updateSupplierFromRequest(SupplierRequest supplierRequest, @MappingTarget Supplier supplier);
    SupplierDTO toSupplierDTO(Supplier supplier);

}
