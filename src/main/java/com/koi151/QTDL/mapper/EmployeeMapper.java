package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.Employee;
import com.koi151.QTDL.model.dto.EmployeeCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    EmployeeCreateDTO toEmployeeDTO(Employee nv);
}
