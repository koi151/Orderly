package com.koi151.QTDL.service.impl;


import com.koi151.QTDL.customExceptions.EntityDeletionFailed;
import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.Role;
import com.koi151.QTDL.mapper.RoleMapper;
import com.koi151.QTDL.model.dto.RoleDTO;
import com.koi151.QTDL.model.request.create.RoleCreateRequest;
import com.koi151.QTDL.model.request.update.RoleUpdateRequest;
import com.koi151.QTDL.repository.RoleRepository;
import com.koi151.QTDL.service.RoleService;
import com.koi151.QTDL.validator.RoleValidator;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Page<RoleDTO> findRoles(Pageable pageable) {
        return roleRepository.findAllByDeleted(false, pageable)
            .map(role -> RoleDTO.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .build());
    }

    @Override
    public RoleDTO createRole(RoleCreateRequest request) {    // Gọi stored procedure để tạo vai trò mới
        Long newRoleId = roleRepository.createRole(
            request.getRoleName(),
            request.getDescription()
        );

        // Chuyển đổi Entity sang DTO và trả về
        return RoleDTO.builder()
            .roleId(newRoleId)
            .roleName(request.getRoleName())
            .description(request.getDescription())
            .build();
    }

//    @Override
//    @Transactional
//    public RoleDTO updateRole(Long roleId, RoleUpdateRequest request) {
//        roleValidator.validateUpdateRoleName(request.getRoleName(), roleId);
//
//        Role existingRole = roleRepository.findById(roleId)
//            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại vai trò quản trị với id: " + roleId));
//
//        Role updatedRole =  roleMapper.updateRoleFromRequest(request, existingRole);
//        Role savedRole = roleRepository.save(updatedRole);
//
//        return roleMapper.toRoleDTO(savedRole);
//    }

    @Override
    @Transactional
    public RoleDTO updateRole(Long roleId, RoleUpdateRequest request) {
     // Gọi stored procedure để cập nhật thông tin role
        roleRepository.updateRole(
            roleId,
            request.getRoleName(),
            request.getDescription()
        );

        // Trả về đối tượng RoleDTO
        return RoleDTO.builder()
            .roleId(roleId)
            .roleName(request.getRoleName())
            .description(request.getDescription())
            .build();
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
