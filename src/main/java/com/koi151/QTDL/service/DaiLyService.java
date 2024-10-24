package com.koi151.QTDL.service;

import com.koi151.QTDL.model.dto.DaiLyCreateDTO;
import com.koi151.QTDL.model.request.DaiLyRequest;

public interface DaiLyService {
    DaiLyCreateDTO taoDaiLy(DaiLyRequest request);
}
