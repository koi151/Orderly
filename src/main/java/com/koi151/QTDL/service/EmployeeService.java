package com.koi151.QTDL.service;

import com.koi151.QTDL.entity.Employee;
import com.koi151.QTDL.model.dto.EmployeeDTO;
import com.koi151.QTDL.model.request.EmployeeLoginRequest;
import com.koi151.QTDL.model.request.create.EmployeeCreateRequest;
import com.koi151.QTDL.model.request.update.EmployeeUpdateRequest;
import com.koi151.QTDL.model.response.LoginResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeService {

    Optional<LoginResponseDTO> login(EmployeeLoginRequest request);
    Page<EmployeeDTO> findEmployees(Pageable pageable);
    EmployeeDTO createEmployee(EmployeeCreateRequest request);
    EmployeeDTO updateEmployee(Long id, EmployeeUpdateRequest request);
    void deleteEmployee(Long id);
}
