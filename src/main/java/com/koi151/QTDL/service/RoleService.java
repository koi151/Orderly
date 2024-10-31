package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.RoleCreateDTO;
import com.koi151.QTDL.model.request.RoleCreateRequest;

public interface RoleService {
    RoleCreateDTO createRole(RoleCreateRequest request);
    void deleteRole(Long id);
}
