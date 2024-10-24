package com.koi151.QTDL.service.impl;


import com.koi151.QTDL.entity.VaiTro;
import com.koi151.QTDL.mapper.VaiTroMapper;
import com.koi151.QTDL.model.dto.VaiTroCreateDTO;
import com.koi151.QTDL.model.request.VaiTroRequest;
import com.koi151.QTDL.repository.VaiTroRepository;
import com.koi151.QTDL.service.VaiTroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaiTroServiceImpl implements VaiTroService {

    private final VaiTroRepository vaiTroRepository;
    private final VaiTroMapper vaiTroMapper;

    @Override
    public VaiTroCreateDTO taoVaiTro(VaiTroRequest request) {
        VaiTro vaiTro = VaiTro.builder()
            .tenVT(request.getTenVT())
            .moTa(request.getMoTa())
            .build();

        VaiTro vt = vaiTroRepository.save(vaiTro);
        return vaiTroMapper.toVaiTroDTO(vt);
    }
}
