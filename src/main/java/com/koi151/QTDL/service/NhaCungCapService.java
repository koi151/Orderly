package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.NhaCungCapCreateDTO;
import com.koi151.QTDL.model.request.NhaCungCapCreateRequest;

public interface NhaCungCapService {
    NhaCungCapCreateDTO taoNhaCungCap(NhaCungCapCreateRequest request);
}
