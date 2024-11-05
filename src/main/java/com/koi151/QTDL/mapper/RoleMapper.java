package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.Role;
import com.koi151.QTDL.model.dto.RoleDTO;
import com.koi151.QTDL.model.request.create.RoleCreateRequest;
import com.koi151.QTDL.model.request.update.RoleUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    RoleDTO toRoleDTO(Role vt);

    @BeanMapping(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    Role updateRoleFromRequest(RoleUpdateRequest request, @MappingTarget Role role);
}
