package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.EmployeeDTO;
import com.koi151.QTDL.model.request.create.EmployeeCreateRequest;
import com.koi151.QTDL.model.request.update.EmployeeUpdateRequest;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeCreateRequest request);
    EmployeeDTO updateEmployee(Long id, EmployeeUpdateRequest request);
    void deleteEmployee(Long id);
}
