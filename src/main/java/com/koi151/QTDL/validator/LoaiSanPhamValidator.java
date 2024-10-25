package com.koi151.QTDL.validator;

import com.koi151.QTDL.customExceptions.InvalidRequestException;
import com.koi151.QTDL.model.request.LoaiSanPhamCreateRequest;
import com.koi151.QTDL.repository.LoaiSanPhamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoaiSanPhamValidator {

    private final LoaiSanPhamRepository loaiSanPhamRepository;

    public void validateLoaiSanPham(LoaiSanPhamCreateRequest request) {
        if (loaiSanPhamRepository.existsByTenLoai(request.getTenLoai()))
            throw new InvalidRequestException("Tên loại sản phẩm đã tồn tại");
    }

}
