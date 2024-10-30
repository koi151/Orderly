package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.NhanVienCreateDTO;
import com.koi151.QTDL.model.request.NhanVienCreateRequest;

public interface NhanVienService {

    NhanVienCreateDTO taoTaiKhoan(NhanVienCreateRequest request);
    void xoaNhanVien(Long id);
}
