package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.EntityAlreadyExistsException;
import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.customExceptions.PasswordMismatchException;
import com.koi151.QTDL.model.request.NhanVienRequest;
import com.koi151.QTDL.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NhanVienValidator {

    private final NhanVienRepository nhanVienRepository;

    public void validateNhanVienRequest(NhanVienRequest request)  {
        if (request == null)
            throw new InvalidRequestException("Request khong duoc de trong");
        if (!request.getRetypePassword().equals(request.getPassword())) {
            throw new PasswordMismatchException("Mat khau xac thuc khong hop le");
        }
    }

    public void validateUniqueNhanVien(NhanVienRequest request) {
        if (nhanVienRepository.existsByEmailOrSdt(request.getEmail(), request.getSdt())) {
            throw new EntityAlreadyExistsException("Sdt hoac email da ton tai");
        }
    }
}
