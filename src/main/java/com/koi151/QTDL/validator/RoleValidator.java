package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.model.request.RoleCreateRequest;
import com.koi151.QTDL.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleValidator {

    private final RoleRepository roleRepository;

    public void validateRole(RoleCreateRequest request) {
        if (roleRepository.existsByRoleName(request.getRoleName()))
            throw new InvalidRequestException("Tên vai trò đã tồn tại");
    }

}
