package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.SanPhamCreateDTO;
import com.koi151.QTDL.model.request.SanPhamCreateRequest;

public interface SanPhamService {
    SanPhamCreateDTO taoSanPham(SanPhamCreateRequest request);
}
