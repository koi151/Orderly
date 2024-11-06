package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.ProductCategory;
import com.koi151.QTDL.model.dto.ProductCategoryDTO;
import com.koi151.QTDL.model.request.update.ProductCategoryUpdateRequest;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCategoryMapper {
    ProductCategoryDTO toCategoryDTO(ProductCategory category);

    @BeanMapping(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    ProductCategory updateCategoryFromRequest(ProductCategoryUpdateRequest request, @MappingTarget ProductCategory category);

    // Map Page<ProductCategory> to Page<ProductCategoryDTO>
    default Page<ProductCategoryDTO> toProductCategoryPage(Page<ProductCategory> categories) {
        List<ProductCategoryDTO> dtoList = categories.stream()
            .map(this::toCategoryDTO)
            .collect(Collectors.toList());
        return new PageImpl<>(dtoList, categories.getPageable(), categories.getTotalElements());
    }
}