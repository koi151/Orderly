package com.koi151.QTDL.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCategoryDTO {
    private Long categoryId;
    private String categoryName;
    private String description;
}
