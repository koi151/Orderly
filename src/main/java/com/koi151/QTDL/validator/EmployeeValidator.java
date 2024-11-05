package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.EntityAlreadyExistsException;
import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.customExceptions.PasswordMismatchException;
import com.koi151.QTDL.model.request.create.EmployeeCreateRequest;
import com.koi151.QTDL.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeValidator {

    private final EmployeeRepository employeeRepository;

    public void validateEmployeeRequest(EmployeeCreateRequest request)  {
        if (!request.getRetypePassword().equals(request.getPassword())) {
            throw new PasswordMismatchException("Mật khẩu xác thực không khớp");
        }
    }

    public void validateUniqueEmployee(EmployeeCreateRequest request) {
        if (employeeRepository.existsByEmailOrPhone(request.getEmail(), request.getPhone())) {
            throw new EntityAlreadyExistsException("Số điện thoại hoặc email đã tồn tại");
        }
    }

    public void validateUpdateEmployeeName(String fullName, String email, Long requestedId) {
        if (employeeRepository.existsByFullNameAndEmailAndEmployeeIdNot(fullName, email, requestedId))
            throw new InvalidRequestException("Số điện thoại hoặc email đã tồn tại");
    }
}
