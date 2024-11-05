package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.Product;
import com.koi151.QTDL.model.dto.ProductDTO;
import com.koi151.QTDL.model.request.update.ProductUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "categoryName", source = "productCategory.categoryName")
    @Mapping(target = "supplierName", source = "supplier.supplierName")
    ProductDTO toProductDTO(Product sp);

    @BeanMapping(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    void updateProductFromRequest(ProductUpdateRequest productRequest, @MappingTarget Product product);
}
