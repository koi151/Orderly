package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.RoleDTO;
import com.koi151.QTDL.model.request.create.RoleCreateRequest;
import com.koi151.QTDL.model.request.update.RoleUpdateRequest;

public interface RoleService {
    RoleDTO createRole(RoleCreateRequest request);
    RoleDTO updateRole(Long id, RoleUpdateRequest request);

    void deleteRole(Long id);
}
