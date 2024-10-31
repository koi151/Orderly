package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.Agency;
import com.koi151.QTDL.mapper.AgencyMapper;
import com.koi151.QTDL.model.dto.AgencyCreateDTO;
import com.koi151.QTDL.model.request.AgencyCreateRequest;
import com.koi151.QTDL.repository.AgencyRepository;
import com.koi151.QTDL.service.AgencyService;
import com.koi151.QTDL.validator.AgencyValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;
    private final AgencyMapper agencyMapper;
    private final AgencyValidator agencyValidator;

    @Override
    @Transactional
    public AgencyCreateDTO createAgency(AgencyCreateRequest request) {
        agencyValidator.validateAgency(request);

        Agency agency = Agency.builder()
            .agencyName(request.getAgencyName())
            .address(request.getAddress())
            .repInfo(request.getRepInfo())
            .phone(request.getPhone())
            .build();

        Agency savedAgencyEntity = agencyRepository.save(agency);
        return agencyMapper.toAgencyCreateDTO(savedAgencyEntity);
    }

    @Override
    public void deleteAgency(Long id) {
        Agency dl = agencyRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy đại lý với id: " + id));

        dl.setDeleted(true);
        agencyRepository.save(dl);
    }
}
