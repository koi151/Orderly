package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.DatHangCreateDTO;
import com.koi151.QTDL.model.request.DatHangCreateRequest;

public interface DatHangService {
    DatHangCreateDTO taoDatHang(DatHangCreateRequest request);
    void xoaDatHang(Long id);
}
