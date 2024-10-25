package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.DaiLyCreateDTO;
import com.koi151.QTDL.model.request.DaiLyCreateRequest;

public interface DaiLyService {
    DaiLyCreateDTO taoDaiLy(DaiLyCreateRequest request);
}
