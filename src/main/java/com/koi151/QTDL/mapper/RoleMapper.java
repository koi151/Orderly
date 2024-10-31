package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.Role;
import com.koi151.QTDL.model.dto.RoleCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    RoleCreateDTO toRoleDTO(Role vt);
}
