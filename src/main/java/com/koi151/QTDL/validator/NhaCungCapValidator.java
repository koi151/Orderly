package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.model.request.NhaCungCapCreateRequest;
import com.koi151.QTDL.repository.NhaCungCapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NhaCungCapValidator {

    private final NhaCungCapRepository nhaCungCapRepository;

    public void validateNhaCungCap(NhaCungCapCreateRequest request) {
        if (nhaCungCapRepository.existsByTenNCC(request.getTenNCC()))
            throw new InvalidRequestException("Tên nhà cung cấp đã tồn tại");
    }

}
