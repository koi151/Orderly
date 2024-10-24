package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.NhanVienCreateDTO;
import com.koi151.QTDL.model.request.NhanVienRequest;

public interface NhanVienService {

    NhanVienCreateDTO taoTaiKhoan(NhanVienRequest request);
}
