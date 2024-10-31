package com.koi151.QTDL.service.impl;


import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.Employee;
import com.koi151.QTDL.mapper.EmployeeMapper;
import com.koi151.QTDL.model.dto.EmployeeCreateDTO;
import com.koi151.QTDL.model.request.EmployeeCreateRequest;
import com.koi151.QTDL.repository.EmployeeRepository;
import com.koi151.QTDL.service.EmployeeService;
import com.koi151.QTDL.utils.PasswordEncoderUtil;
import com.koi151.QTDL.validator.EmployeeValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoderUtil encoderUtil;
    private final EmployeeValidator employeeValidator;

    @Override
    @Transactional
    public EmployeeCreateDTO createEmployee(EmployeeCreateRequest request) {
        employeeValidator.validateNhanVienRequest(request);
        employeeValidator.validateUniqueNhanVien(request);

        Employee nv = Employee.builder()
            .password(encoderUtil.encodePassword(request.getPassword()))
            .phone(request.getPhone())
            .fullName(request.getFullName())
            .email(request.getEmail())
            .build();

        Employee savedNV = employeeRepository.save(nv);
        return employeeMapper.toEmployeeDTO(savedNV);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee nv = employeeRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhân viên với id: " + id));

        nv.setDeleted(true);
        employeeRepository.save(nv);
    }
}
