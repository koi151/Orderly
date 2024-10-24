package com.koi151.QTDL.service.impl;


import com.koi151.QTDL.entity.NhanVien;
import com.koi151.QTDL.mapper.NhanVienMapper;
import com.koi151.QTDL.model.dto.NhanVienCreateDTO;
import com.koi151.QTDL.model.request.NhanVienRequest;
import com.koi151.QTDL.repository.NhanVienRepository;
import com.koi151.QTDL.service.NhanVienService;
import com.koi151.QTDL.utils.PasswordEncoderUtil;
import com.koi151.QTDL.validator.NhanVienValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NhanVienServiceImpl implements NhanVienService {

    private final NhanVienRepository nhanVienRepository;
    private final NhanVienMapper nhanVienMapper;
    private final PasswordEncoderUtil encoderUtil;
    private final NhanVienValidator nhanVienValidator;

    @Override
    public NhanVienCreateDTO taoTaiKhoan(NhanVienRequest request) {
        nhanVienValidator.validateNhanVienRequest(request);
        nhanVienValidator.validateUniqueNhanVien(request);

        NhanVien nv = NhanVien.builder()
            .matKhau(encoderUtil.encodePassword(request.getPassword()))
            .sdt(request.getSdt())
            .hoTen(request.getHoTen())
            .email(request.getEmail())
            .build();

        NhanVien savedNV = nhanVienRepository.save(nv);
        return nhanVienMapper.toNhanVienDTO(savedNV);
    }
}
