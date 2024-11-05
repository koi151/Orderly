package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.Agency;
import com.koi151.QTDL.mapper.AgencyMapper;
import com.koi151.QTDL.model.dto.AgencyDTO;
import com.koi151.QTDL.model.request.create.AgencyCreateRequest;
import com.koi151.QTDL.model.request.update.AgencyUpdateRequest;
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
    public AgencyDTO createAgency(AgencyCreateRequest request) {
        agencyValidator.validateAgencyName(request.getAgencyName());

        Agency agency = Agency.builder()
            .agencyName(request.getAgencyName())
            .address(request.getAddress())
            .repInfo(request.getRepInfo())
            .phone(request.getPhone())
            .build();

        Agency savedAgencyEntity = agencyRepository.save(agency);
        return agencyMapper.toAgencyDTO(savedAgencyEntity);
    }

    @Override
    @Transactional
    public AgencyDTO updateAgency(Long agencyId, AgencyUpdateRequest request) {
        agencyValidator.validateUpdateAgencyName(request.getAgencyName(), agencyId);

        Agency existingAgency = agencyRepository.findById(agencyId)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại đại lý với id: " + agencyId));

        Agency updatedAgency = agencyMapper.updateAgencyFromRequest(request, existingAgency);
        Agency savedAgency = agencyRepository.save(updatedAgency);

        return agencyMapper.toAgencyDTO(savedAgency);
    }

    @Override
    public void deleteAgency(Long id) {
        Agency existingAgency = agencyRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy đại lý với id: " + id));

        existingAgency.setDeleted(true);
        agencyRepository.save(existingAgency);
    }
}
