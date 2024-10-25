package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.EntityAlreadyExistsException;
import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.customExceptions.PasswordMismatchException;
import com.koi151.QTDL.model.request.NhanVienCreateRequest;
import com.koi151.QTDL.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NhanVienValidator {

    private final NhanVienRepository nhanVienRepository;

    public void validateNhanVienRequest(NhanVienCreateRequest request)  {
        if (!request.getRetypePassword().equals(request.getPassword())) {
            throw new PasswordMismatchException("Mật khẩu xác thực không khớp");
        }
    }

    public void validateUniqueNhanVien(NhanVienCreateRequest request) {
        if (nhanVienRepository.existsByEmailOrSdt(request.getEmail(), request.getSdt())) {
            throw new EntityAlreadyExistsException("Sdt hoặc email đã tồn tại");
        }
    }
}
