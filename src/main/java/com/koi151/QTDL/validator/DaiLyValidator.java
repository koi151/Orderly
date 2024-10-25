package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.model.request.DaiLyCreateRequest;
import com.koi151.QTDL.repository.DaiLyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DaiLyValidator {

    private final DaiLyRepository daiLyRepository;

    public void validateDaiLy(DaiLyCreateRequest request) {
        if (daiLyRepository.existsByTenDL(request.getTenDL()))
            throw new InvalidRequestException("Tên đại lý đã tồn tại");
    }

}
