package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.model.request.create.AgencyCreateRequest;
import com.koi151.QTDL.repository.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgencyValidator {

    private final AgencyRepository agencyRepository;

    public void validateAgencyName(String name) {
        if (agencyRepository.existsByAgencyName(name))
            throw new InvalidRequestException("Tên đại lý đã tồn tại");
    }

    public void validateUpdateAgencyName(String name, Long requestedId) {
        if (agencyRepository.existsByAgencyNameAndAgencyIdNot(name, requestedId))
            throw new InvalidRequestException("Tên đại lý đã tồn tại");
    }

}
