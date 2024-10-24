package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.entity.DaiLy;
import com.koi151.QTDL.mapper.DaiLyMapper;
import com.koi151.QTDL.model.dto.DaiLyCreateDTO;
import com.koi151.QTDL.model.request.DaiLyRequest;
import com.koi151.QTDL.repository.DaiLyRepository;
import com.koi151.QTDL.service.DaiLyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DaiLyServiceImpl implements DaiLyService {

    private final DaiLyRepository daiLyRepository;
    private final DaiLyMapper daiLyMapper;

    @Override
    public DaiLyCreateDTO taoDaiLy(DaiLyRequest request) {
        DaiLy daiLy = DaiLy.builder()
            .tenDL(request.getTenDL())
            .diaChi(request.getDiaChi())
            .nguoiDaiDien(request.getNguoiDaiDien())
            .sdt(request.getSdt())
            .build();

        DaiLy savedDaiLyEntity = daiLyRepository.save(daiLy);
        return daiLyMapper.toDaiLyCreateDTO(savedDaiLyEntity);
    }
}
