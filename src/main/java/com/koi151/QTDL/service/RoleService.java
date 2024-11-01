package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.RoleDTO;
import com.koi151.QTDL.model.request.ProductCategoryRequest;
import com.koi151.QTDL.model.request.RoleRequest;

public interface RoleService {
    RoleDTO createRole(RoleRequest request);
    RoleDTO updateRole(Long id, RoleRequest request);

    void deleteRole(Long id);
}
