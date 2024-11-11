package com.koi151.QTDL.service.impl;


import com.koi151.QTDL.config.JwtTokenProvider;
import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.*;
import com.koi151.QTDL.mapper.EmployeeMapper;
import com.koi151.QTDL.model.dto.EmployeeDTO;
import com.koi151.QTDL.model.dto.ProductDTO;
import com.koi151.QTDL.model.request.EmployeeLoginRequest;
import com.koi151.QTDL.model.request.create.EmployeeCreateRequest;
import com.koi151.QTDL.model.request.update.EmployeeUpdateRequest;
import com.koi151.QTDL.model.request.update.ProductUpdateRequest;
import com.koi151.QTDL.model.response.LoginResponseDTO;
import com.koi151.QTDL.repository.EmployeeRepository;
import com.koi151.QTDL.repository.RoleRepository;
import com.koi151.QTDL.service.EmployeeService;
import com.koi151.QTDL.utils.PasswordEncoderUtil;
import com.koi151.QTDL.validator.EmployeeValidator;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoderUtil encoderUtil;
    private final EmployeeValidator employeeValidator;
    private final EntityManager entityManager;
    private final JwtTokenProvider jwtTokenProvider;

//    @Override
//    @Transactional
//    public EmployeeDTO createEmployee(EmployeeCreateRequest request) {
//        employeeValidator.validateEmployeeRequest(request);
//        employeeValidator.validateUniqueEmployee(request);
//
//        var role = roleRepository.findByRoleIdAndDeleted(request.getRoleId(), false)
//            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại vai trò quản trị với mã: " + request.getRoleId()));
//
//        Employee nv = Employee.builder()
//            .password(encoderUtil.encodePassword(request.getPassword()))
//            .phone(request.getPhone())
//            .role(role)
//            .fullName(request.getFullName())
//            .email(request.getEmail())
//            .build();
//
//        Employee savedNV = employeeRepository.save(nv);
//        return employeeMapper.toEmployeeDTO(savedNV);
//    }

    @Override
    public Optional<LoginResponseDTO> login(EmployeeLoginRequest loginReq) {
        Employee user = employeeRepository.findByEmailAndDeletedFalse(loginReq.getEmail())
            .orElseThrow(() -> new EntityNotExistedException("Email không tồn tại"));

        // Kiểm tra mật khẩu
        if (encoderUtil.matches(loginReq.getPassword(), user.getPassword())) {
            // Tạo token nếu đăng nhập thành công
            String token = jwtTokenProvider.generateToken(user.getEmail());

            // Chuyển đổi thông tin người dùng sang EmployeeDTO
            EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .employeeId(user.getEmployeeId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();

            // Tạo đối tượng LoginResponseDTO với token và thông tin người dùng
            LoginResponseDTO responseDTO = LoginResponseDTO.builder()
                .token(token)
                .employee(employeeDTO)
                .build();

            return Optional.of(responseDTO);
        }

        // Trả về Optional rỗng nếu mật khẩu không đúng
        return Optional.empty();
    }

    public Page<EmployeeDTO> findEmployees(Pageable pageable) {
        return employeeRepository.findAllByDeleted(false, pageable)
            .map(employee -> EmployeeDTO.builder()
                .employeeId(employee.getEmployeeId())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .build()
            );
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeCreateRequest request) {
        // Kiểm tra mật khẩu xác thực
        employeeValidator.validateEmployeeRequest(request);

        // Mã hóa mật khẩu
        String encodedPassword = encoderUtil.encodePassword(request.getPassword());

        // Gọi stored procedure để thêm nhân viên mới và nhận employee_id vừa tạo
        Long employeeId = employeeRepository.createEmployee(
            request.getFullName(),
            request.getEmail(),
            request.getPhone(),
            encodedPassword,
            request.getRoleId()
        );

        return EmployeeDTO.builder()
            .employeeId(employeeId)
            .fullName(request.getFullName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .build();
    }


//    @Override
//    @Transactional
//    public EmployeeDTO updateEmployee(Long id, EmployeeUpdateRequest request) {
//        Employee existingEmployee = employeeRepository.findByEmployeeIdAndDeleted(id, false)
//            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại vai trò quản trị với id: " + id));
//
//
//        employeeValidator.validateUpdateEmployeeName(request.getFullName(), request.getEmail(), id);
//
//        if (request.getRoleId() != null) {
//             Role role = roleRepository.findById(request.getRoleId())
//                .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy vai trò quản trị với mã: " + request.getRoleId()));
//            existingEmployee.setRole(role);
//        }
//
//        // Use MapStruct to update non-null fields from request
//        employeeMapper.updateEmployeeFromRequest(request, existingEmployee);
//
//        Employee savedEmployee = employeeRepository.save(existingEmployee);
//        return employeeMapper.toEmployeeDTO(savedEmployee);
//    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeUpdateRequest request) {
        // Kiểm tra xem nhân viên có tồn tại không
        Employee existingEmployee = employeeRepository.findByEmployeeIdAndDeleted(employeeId, false)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại nhân viên với id: " + employeeId));

        // Kiểm tra trùng lặp tên hoặc email
        employeeValidator.validateUpdateEmployeeName(request.getFullName(), request.getEmail(), employeeId);

        // Kiểm tra nếu roleId có tồn tại, và xác thực vai trò
        if (request.getRoleId() != null) {
            roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy vai trò với mã: " + request.getRoleId()));
        }

        // Mã hóa mật khẩu nếu có thay đổi
        String encodedPassword = request.getPassword() != null ? encoderUtil.encodePassword(request.getPassword()) : null;

        // Gọi stored procedure để cập nhật thông tin nhân viên
        employeeRepository.updateEmployee(
            employeeId,
            request.getFullName(),
            request.getEmail(),
            request.getPhone(),
            encodedPassword,
            request.getRoleId()
        );

        // Sử dụng flush và clear để đảm bảo thay đổi được lưu ngay lập tức và làm mới cache
        entityManager.flush();
        entityManager.clear();

        // Lấy lại thông tin nhân viên đã cập nhật từ cơ sở dữ liệu
        Employee updatedEmployee = employeeRepository.findByEmployeeIdAndDeleted(employeeId, false)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại nhân viên với id: " + employeeId));

        // Trả về đối tượng EmployeeDTO
        return employeeMapper.toEmployeeDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee nv = employeeRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhân viên với id: " + id));

        nv.setDeleted(true);
        employeeRepository.save(nv);
    }
}
