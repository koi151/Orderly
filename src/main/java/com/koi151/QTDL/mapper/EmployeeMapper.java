package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.Employee;
import com.koi151.QTDL.entity.Product;
import com.koi151.QTDL.model.dto.EmployeeDTO;
import com.koi151.QTDL.model.request.update.EmployeeUpdateRequest;
import com.koi151.QTDL.model.request.update.ProductUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    EmployeeDTO toEmployeeDTO(Employee em);

    @BeanMapping(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    void updateEmployeeFromRequest(EmployeeUpdateRequest productRequest, @MappingTarget Employee em);
}
