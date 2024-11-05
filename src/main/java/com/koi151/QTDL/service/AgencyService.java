package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.AgencyDTO;
import com.koi151.QTDL.model.request.create.AgencyCreateRequest;
import com.koi151.QTDL.model.request.update.AgencyUpdateRequest;

public interface AgencyService {
    AgencyDTO createAgency(AgencyCreateRequest request);

    AgencyDTO updateAgency(Long id, AgencyUpdateRequest request);

    void deleteAgency(Long id);
}
