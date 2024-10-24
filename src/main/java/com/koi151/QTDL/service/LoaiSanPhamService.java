package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.LoaiSanPhamCreateDTO;
import com.koi151.QTDL.model.request.LoaiSanPhamRequest;

public interface LoaiSanPhamService {
    LoaiSanPhamCreateDTO taoLoaiSP(LoaiSanPhamRequest request);
}
