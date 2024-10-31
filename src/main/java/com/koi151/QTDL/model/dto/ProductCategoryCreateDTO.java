package com.koi151.QTDL.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCategoryCreateDTO {
    private Long categoryId;
    private String categoryName;
}
