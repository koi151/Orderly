package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.entity.NhaCungCap;
import com.koi151.QTDL.mapper.NhaCungCapMapper;
import com.koi151.QTDL.model.dto.NhaCungCapCreateDTO;
import com.koi151.QTDL.model.request.NhaCungCapRequest;
import com.koi151.QTDL.repository.NhaCungCapRepository;
import com.koi151.QTDL.service.NhaCungCapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NhaCungCapServiceImpl implements NhaCungCapService {

    private final NhaCungCapRepository nhaCungCapRepository;
    private final NhaCungCapMapper nhaCungCapMapper;

    @Override
    public NhaCungCapCreateDTO taoNhaCungCap(NhaCungCapRequest request) {
        NhaCungCap ncc = NhaCungCap.builder()
            .tenNCC(request.getTenNCC())
            .diaChi(request.getDiaChi())
            .TTNguoiDaiDien(request.getTTNguoiDaiDien())
            .build();

        NhaCungCap savedNCCEntity = nhaCungCapRepository.save(ncc);
        return nhaCungCapMapper.toNhaCungCapCreateDTO(savedNCCEntity);
    }
}
