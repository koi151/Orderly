package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.ProductCategory;
import com.koi151.QTDL.model.dto.ProductCategoryDTO;
import com.koi151.QTDL.model.request.update.ProductCategoryUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCategoryMapper {
    ProductCategoryDTO toCategoryDTO(ProductCategory category);

    @BeanMapping(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    ProductCategory updateCategoryFromRequest(ProductCategoryUpdateRequest request, @MappingTarget ProductCategory category);

    ProductCategoryDTO toProductCategoryDTO(ProductCategory category);
}
