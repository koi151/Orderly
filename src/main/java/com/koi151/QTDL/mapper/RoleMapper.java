package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.ProductCategory;
import com.koi151.QTDL.entity.Role;
import com.koi151.QTDL.model.dto.RoleDTO;
import com.koi151.QTDL.model.request.ProductCategoryRequest;
import com.koi151.QTDL.model.request.RoleRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    RoleDTO toRoleDTO(Role vt);

    @BeanMapping(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    Role updateRoleFromRequest(RoleRequest request, @MappingTarget Role role);
}
