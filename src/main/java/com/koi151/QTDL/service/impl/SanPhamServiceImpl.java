package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityAlreadyExistsException;
import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.LoaiSanPham;
import com.koi151.QTDL.entity.NhaCungCap;
import com.koi151.QTDL.entity.SanPham;
import com.koi151.QTDL.mapper.SanPhamMapper;
import com.koi151.QTDL.model.dto.SanPhamCreateDTO;
import com.koi151.QTDL.model.request.SanPhamCreateRequest;
import com.koi151.QTDL.repository.LoaiSanPhamRepository;
import com.koi151.QTDL.repository.NhaCungCapRepository;
import com.koi151.QTDL.repository.SanPhamRepository;
import com.koi151.QTDL.service.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SanPhamServiceImpl implements SanPhamService {

    private final SanPhamRepository sanPhamRepository;
    private final LoaiSanPhamRepository loaiSanPhamRepository;
    private final NhaCungCapRepository nhaCungCapRepository;
    private final SanPhamMapper sanPhamMapper;

    @Override
    public SanPhamCreateDTO taoSanPham(SanPhamCreateRequest request) {
        if (sanPhamRepository.existsByTenSP(request.getTenSP()))
            throw new EntityAlreadyExistsException("Tên sản phẩm đã được sử dụng");

        LoaiSanPham loaiSanPham = loaiSanPhamRepository.findById(request.getMaLoai())
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy loại sản phẩm với mã: " + request.getMaLoai()));

        NhaCungCap nhaCungCap = nhaCungCapRepository.findById(request.getMaNCC())
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhà cung cấp với mã: " + request.getMaNCC()));

        SanPham sp = SanPham.builder()
            .tenSP(request.getTenSP())
            .nhaCungCap(nhaCungCap)
            .loaiSanPham(loaiSanPham)
            .gia(request.getGia())
            .build();

        SanPham savedSPEntity = sanPhamRepository.save(sp);
        return SanPhamCreateDTO.builder()
            .tenSP(savedSPEntity.getTenSP())
            .tenNCC(savedSPEntity.getNhaCungCap().getTenNCC())
            .tenLoaiSP(savedSPEntity.getLoaiSanPham().getTenLoai())
            .gia(savedSPEntity.getGia())
            .build();
    }
}
