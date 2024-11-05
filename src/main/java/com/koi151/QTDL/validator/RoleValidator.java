package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.model.request.create.RoleCreateRequest;
import com.koi151.QTDL.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleValidator {

    private final RoleRepository roleRepository;

    public void validateRoleName(String name) {
        if (roleRepository.existsByRoleName(name))
            throw new InvalidRequestException("Tên vai trò quản trị đã tồn tại");
    }

    public void validateUpdateRoleName(String name, Long requestedId) {
        if (roleRepository.existsByRoleNameAndRoleIdNot(name, requestedId))
            throw new InvalidRequestException("Tên vai trò quản trị đã tồn tại");
    }

}
