package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.VaiTroCreateDTO;
import com.koi151.QTDL.model.request.VaiTroCreateRequest;

public interface VaiTroService {
    VaiTroCreateDTO taoVaiTro(VaiTroCreateRequest request);
    void xoaVaiTroQuanTri(Long id);
}
