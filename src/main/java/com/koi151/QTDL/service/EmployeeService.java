package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.EmployeeCreateDTO;
import com.koi151.QTDL.model.request.EmployeeCreateRequest;

public interface EmployeeService {

    EmployeeCreateDTO createEmployee(EmployeeCreateRequest request);
    void deleteEmployee(Long id);
}
