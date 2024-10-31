package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.EntityAlreadyExistsException;
import com.koi151.QTDL.customExceptions.PasswordMismatchException;
import com.koi151.QTDL.model.request.EmployeeCreateRequest;
import com.koi151.QTDL.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeValidator {

    private final EmployeeRepository employeeRepository;

    public void validateNhanVienRequest(EmployeeCreateRequest request)  {
        if (!request.getRetypePassword().equals(request.getPassword())) {
            throw new PasswordMismatchException("Mật khẩu xác thực không khớp");
        }
    }

    public void validateUniqueNhanVien(EmployeeCreateRequest request) {
        if (employeeRepository.existsByEmailOrPhone(request.getEmail(), request.getPhone())) {
            throw new EntityAlreadyExistsException("Phone hoặc email đã tồn tại");
        }
    }
}
