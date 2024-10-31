package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.AgencyCreateDTO;
import com.koi151.QTDL.model.request.AgencyCreateRequest;

public interface AgencyService {
    AgencyCreateDTO createAgency(AgencyCreateRequest request);
    void deleteAgency(Long id);
}
