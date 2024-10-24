package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.VaiTroCreateDTO;
import com.koi151.QTDL.model.request.VaiTroRequest;

public interface VaiTroService {
    VaiTroCreateDTO taoVaiTro(VaiTroRequest request);
}
