package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.model.request.DaiLyCreateRequest;
import com.koi151.QTDL.model.request.VaiTroCreateRequest;
import com.koi151.QTDL.repository.DaiLyRepository;
import com.koi151.QTDL.repository.VaiTroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VaiTroValidator {

    private final VaiTroRepository vaiTroRepository;

    public void validateVaiTro(VaiTroCreateRequest request) {
        if (vaiTroRepository.existsByTenVT(request.getTenVT()))
            throw new InvalidRequestException("Tên vai trò đã tồn tại");
    }

}
