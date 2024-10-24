package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.entity.LoaiSanPham;
import com.koi151.QTDL.mapper.LoaiSanPhamMapper;
import com.koi151.QTDL.model.dto.LoaiSanPhamCreateDTO;
import com.koi151.QTDL.model.request.LoaiSanPhamRequest;
import com.koi151.QTDL.repository.LoaiSanPhamRepository;
import com.koi151.QTDL.service.LoaiSanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService {

    private final LoaiSanPhamRepository loaiSanPhamRepository;
    private final LoaiSanPhamMapper loaiSanPhamMapper;

    @Override
    public LoaiSanPhamCreateDTO taoLoaiSP(LoaiSanPhamRequest request) {
        LoaiSanPham loaiSP = LoaiSanPham.builder()
            .tenLoai(request.getTenLoai())
            .build();

        LoaiSanPham loaiSPsaved = loaiSanPhamRepository.save(loaiSP);
        return loaiSanPhamMapper.toLoaiSanPhamDTO(loaiSPsaved);
    }
}
