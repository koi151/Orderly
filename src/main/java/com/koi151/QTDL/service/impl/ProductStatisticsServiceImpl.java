package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.model.dto.ProductStatisticsDTO;
import com.koi151.QTDL.repository.ProductStatisticsRepository;
import com.koi151.QTDL.service.ProductStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductStatisticsServiceImpl implements ProductStatisticsService {

    private final ProductStatisticsRepository productStatisticsRepository;

    @Override
    public ProductStatisticsDTO getProductStatistics() {
        return productStatisticsRepository.getProductStatistics();
    }
}
