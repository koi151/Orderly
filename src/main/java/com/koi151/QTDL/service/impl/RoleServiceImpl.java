package com.koi151.QTDL.service.impl;


import com.koi151.QTDL.customExceptions.EntityDeletionFailed;
import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.ProductCategory;
import com.koi151.QTDL.entity.Role;
import com.koi151.QTDL.mapper.RoleMapper;
import com.koi151.QTDL.model.dto.ProductCategoryDTO;
import com.koi151.QTDL.model.dto.RoleDTO;
import com.koi151.QTDL.model.request.ProductCategoryRequest;
import com.koi151.QTDL.model.request.RoleRequest;
import com.koi151.QTDL.repository.RoleRepository;
import com.koi151.QTDL.service.RoleService;
import com.koi151.QTDL.validator.RoleValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final RoleValidator roleValidator;

    @Override
    @Transactional
    public RoleDTO createRole(RoleRequest request) {
        roleValidator.validateRole(request);

        Role role = Role.builder()
            .roleName(request.getRoleName())
            .description(request.getDescription())
            .build();

        Role vt = roleRepository.save(role);
        return roleMapper.toRoleDTO(vt);
    }

    @Override
    @Transactional
    public RoleDTO updateRole(Long roleId, RoleRequest request) {
        roleValidator.validateRole(request);

        Role existingRole = roleRepository.findById(roleId)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại vai trò quản trị với id: " + roleId));

        Role updatedRole =  roleMapper.updateRoleFromRequest(request, existingRole);
        Role savedRole = roleRepository.save(updatedRole);

        return roleMapper.toRoleDTO(savedRole);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        Role existedRole = roleRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Vai trò quản trị không tồn tại với id: " + id));

        Long employeesCnt = roleRepository.countEmployeesByRoleId(id);

        if (employeesCnt == 0) {
            existedRole.setDeleted(true);
            roleRepository.save(existedRole);
        } else {
            throw new EntityDeletionFailed("ID vai trò quản trị đã được sử dụng bởi " + employeesCnt
                + " nhân viên. Sửa vai trò quản trị các nhân viên trên trước khi xóa vai trò quản trị với id: " + id);
        }
    }
}
