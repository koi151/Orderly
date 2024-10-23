package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.NhanVienDTO;
import com.koi151.QTDL.model.request.NhanVienRequest;

public interface NhanVienService {

    NhanVienDTO taoTaiKhoan(NhanVienRequest request);
}
