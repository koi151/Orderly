package com.koi151.QTDL.mapper;

import com.koi151.QTDL.entity.DaiLy;
import com.koi151.QTDL.model.dto.DaiLyCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DaiLyMapper {

    DaiLyCreateDTO toDaiLyCreateDTO(DaiLy daiLy);
}
