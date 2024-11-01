package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.Supplier;
import com.koi151.QTDL.model.dto.SupplierDTO;
import com.koi151.QTDL.model.request.SupplierRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierMapper {
    SupplierDTO toSupplierCreateDTO(Supplier entity);

    @BeanMapping(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    Supplier updateSupplierFromRequest(SupplierRequest supplierRequest, @MappingTarget Supplier supplier);
    SupplierDTO toSupplierDTO(Supplier supplier);

}
