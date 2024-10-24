package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.NhaCungCapCreateDTO;
import com.koi151.QTDL.model.request.NhaCungCapRequest;

public interface NhaCungCapService {
    NhaCungCapCreateDTO taoNhaCungCap(NhaCungCapRequest request);
}
