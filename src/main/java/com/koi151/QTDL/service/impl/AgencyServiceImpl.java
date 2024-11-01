package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.Agency;
import com.koi151.QTDL.entity.ProductCategory;
import com.koi151.QTDL.mapper.AgencyMapper;
import com.koi151.QTDL.model.dto.AgencyDTO;
import com.koi151.QTDL.model.dto.ProductCategoryDTO;
import com.koi151.QTDL.model.request.AgencyRequest;
import com.koi151.QTDL.model.request.ProductCategoryRequest;
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
    public AgencyDTO createAgency(AgencyRequest request) {
        agencyValidator.validateAgency(request);

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
    public AgencyDTO updateAgency(Long agencyId, AgencyRequest request) {
        agencyValidator.validateAgency(request);

        Agency existingAgency = agencyRepository.findById(agencyId)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại đại lý với id: " + agencyId));

        Agency updatedAgency = agencyMapper.updateAgencyFromRequest(request, existingAgency);
        Agency savedAgency = agencyRepository.save(updatedAgency);

        return agencyMapper.toAgencyDTO(savedAgency);
    }

    @Override
    public void deleteAgency(Long id) {
        Agency dl = agencyRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy đại lý với id: " + id));

        dl.setDeleted(true);
        agencyRepository.save(dl);
    }
}
