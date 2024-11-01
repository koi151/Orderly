package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.AgencyDTO;
import com.koi151.QTDL.model.dto.ProductCategoryDTO;
import com.koi151.QTDL.model.request.AgencyRequest;
import com.koi151.QTDL.model.request.ProductCategoryRequest;

public interface AgencyService {
    AgencyDTO createAgency(AgencyRequest request);

    AgencyDTO updateAgency(Long id, AgencyRequest request);

    void deleteAgency(Long id);
}
