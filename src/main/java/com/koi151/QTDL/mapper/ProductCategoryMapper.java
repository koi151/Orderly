package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.ProductCategory;
import com.koi151.QTDL.model.dto.ProductCategoryDTO;
import com.koi151.QTDL.model.request.ProductCategoryRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCategoryMapper {
    ProductCategoryDTO toCategoryDTO(ProductCategory category);

    @BeanMapping(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    ProductCategory updateCategoryFromRequest(ProductCategoryRequest request, @MappingTarget ProductCategory category);

    ProductCategoryDTO toProductCategoryDTO(ProductCategory category);
}
