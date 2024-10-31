package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.model.request.AgencyCreateRequest;
import com.koi151.QTDL.repository.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgencyValidator {

    private final AgencyRepository agencyRepository;

    public void validateAgency(AgencyCreateRequest request) {
        if (agencyRepository.existsByagencyName(request.getAgencyName()))
            throw new InvalidRequestException("Tên đại lý đã tồn tại");
    }

}
