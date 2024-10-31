package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.Product;
import com.koi151.QTDL.model.dto.ProductCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductCreateDTO toProductCreateDTO(Product sp);
}
