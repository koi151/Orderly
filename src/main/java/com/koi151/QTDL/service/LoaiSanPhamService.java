package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.LoaiSanPhamCreateDTO;
import com.koi151.QTDL.model.request.LoaiSanPhamCreateRequest;

public interface LoaiSanPhamService {
    LoaiSanPhamCreateDTO taoLoaiSP(LoaiSanPhamCreateRequest request);
}
