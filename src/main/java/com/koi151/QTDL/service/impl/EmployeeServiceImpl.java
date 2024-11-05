package com.koi151.QTDL.service.impl;


import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.*;
import com.koi151.QTDL.mapper.EmployeeMapper;
import com.koi151.QTDL.model.dto.EmployeeDTO;
import com.koi151.QTDL.model.dto.ProductDTO;
import com.koi151.QTDL.model.request.create.EmployeeCreateRequest;
import com.koi151.QTDL.model.request.update.EmployeeUpdateRequest;
import com.koi151.QTDL.model.request.update.ProductUpdateRequest;
import com.koi151.QTDL.repository.EmployeeRepository;
import com.koi151.QTDL.repository.RoleRepository;
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
    private final RoleRepository roleRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoderUtil encoderUtil;
    private final EmployeeValidator employeeValidator;

    @Override
    @Transactional
    public EmployeeDTO createEmployee(EmployeeCreateRequest request) {
        employeeValidator.validateEmployeeRequest(request);
        employeeValidator.validateUniqueEmployee(request);

        var role = roleRepository.findByRoleIdAndDeleted(request.getRoleId(), false)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại vai trò quản trị với mã: " + request.getRoleId()));

        Employee nv = Employee.builder()
            .password(encoderUtil.encodePassword(request.getPassword()))
            .phone(request.getPhone())
            .role(role)
            .fullName(request.getFullName())
            .email(request.getEmail())
            .build();

        Employee savedNV = employeeRepository.save(nv);
        return employeeMapper.toEmployeeDTO(savedNV);
    }


    @Override
    @Transactional
    public EmployeeDTO updateEmployee(Long id, EmployeeUpdateRequest request) {
        Employee existingEmployee = employeeRepository.findByEmployeeIdAndDeleted(id, false)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại vai trò quản trị với id: " + id));


        employeeValidator.validateUpdateEmployeeName(request.getFullName(), request.getEmail(), id);

        if (request.getRoleId() != null) {
             Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy vai trò quản trị với mã: " + request.getRoleId()));
            existingEmployee.setRole(role);
        }

        // Use MapStruct to update non-null fields from request
        employeeMapper.updateEmployeeFromRequest(request, existingEmployee);

        Employee savedEmployee = employeeRepository.save(existingEmployee);
        return employeeMapper.toEmployeeDTO(savedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee nv = employeeRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhân viên với id: " + id));

        nv.setDeleted(true);
        employeeRepository.save(nv);
    }
}
