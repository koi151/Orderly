package com.koi151.QTDL.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koi151.QTDL.model.dto.ProductStatisticsDTO;
import com.koi151.QTDL.repository.ProductStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductStatisticsRepositoryImpl implements ProductStatisticsRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public ProductStatisticsDTO getProductStatistics() {
        String sql = "SELECT get_product_statistics() AS stats";

        String jsonResult = jdbcTemplate.queryForObject(sql, String.class);

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonResult);
            return ProductStatisticsDTO.builder()
                .totalProducts(jsonNode.get("totalProducts").asLong())
                .averagePrice(jsonNode.get("averagePrice").decimalValue())
                .minPrice(jsonNode.get("minPrice").decimalValue())
                .maxPrice(jsonNode.get("maxPrice").decimalValue())
                .productsPerCategory(objectMapper.convertValue(jsonNode.get("productsPerCategory"),
                    new TypeReference<Map<String, Long>>() {}))
                .productsPerSupplier(objectMapper.convertValue(jsonNode.get("productsPerSupplier"),
                    new TypeReference<Map<String, Long>>() {}))
                .build();
        } catch (IOException e) {
            throw new RuntimeException("Không thể chuyển thông tin thống kê sang JSON", e);
        }
    }
}
