package com.koi151.QTDL.service.impl;

import com.koi151.QTDL.customExceptions.EntityNotExistedException;
import com.koi151.QTDL.entity.CTDatHang;
import com.koi151.QTDL.entity.DaiLy;
import com.koi151.QTDL.entity.DatHang;
import com.koi151.QTDL.entity.NhanVien;
import com.koi151.QTDL.entity.keys.CTDatHangKey;
import com.koi151.QTDL.enums.TrangThaiDatHangEnum;
import com.koi151.QTDL.model.dto.DatHangCreateDTO;
import com.koi151.QTDL.model.request.DatHangCreateRequest;
import com.koi151.QTDL.repository.DaiLyRepository;
import com.koi151.QTDL.repository.DatHangRepository;
import com.koi151.QTDL.repository.NhanVienRepository;
import com.koi151.QTDL.repository.SanPhamRepository;
import com.koi151.QTDL.service.DatHangService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class DatHangServiceImpl implements DatHangService {

    private final DatHangRepository datHangRepository;
    private final DaiLyRepository daiLyRepository;
    private final NhanVienRepository nhanVienRepository;
    private final SanPhamRepository sanPhamRepository;

    @Override
    @Transactional
    public DatHangCreateDTO taoDatHang(DatHangCreateRequest request) {
        DaiLy daiLy = daiLyRepository.findById(request.getMaDL())
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy đại lý với mã: " + request.getMaDL()));

        NhanVien nhanVien = nhanVienRepository.findById(request.getMaNV())
            .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy nhân viên với mã: " + request.getMaNV()));

        DatHang datHang = DatHang.builder()
            .daiLy(daiLy)
            .nhanVien(nhanVien)
            .ghiChu(request.getGhiChu())
            .dsCTDatHang(new ArrayList<>())
            .trangThai(TrangThaiDatHangEnum.dangXuLy)
            .build();

        request.getDsCTDatHang().forEach(chiTietRequest -> {
            var sanPham = sanPhamRepository.findById(chiTietRequest.getMaSP())
                .orElseThrow(() -> new EntityNotExistedException("Không tìm thấy sản phẩm với mã: " + chiTietRequest.getMaSP()));

            var ctDatHangKey = new CTDatHangKey(datHang.getMaDH(), sanPham.getMaSP());
            var ctDatHang = CTDatHang.builder()
                .ctDatHangKey(ctDatHangKey)
                .datHang(datHang)
                .sanPham(sanPham)
                .ngayGiaoHang(chiTietRequest.getNgayGiaoHang())
                .soLg(chiTietRequest.getSoLg())
                .build();

            datHang.getDsCTDatHang().add(ctDatHang);
        });

        DatHang savedDatHangEntity = datHangRepository.save(datHang);
        return DatHangCreateDTO.builder()
            .maDH(savedDatHangEntity.getMaDH())
            .tenDL(savedDatHangEntity.getDaiLy().getTenDL())
            .tenNV(savedDatHangEntity.getNhanVien().getHoTen())
            .sdtNV(savedDatHangEntity.getNhanVien().getSdt())
            .ghiChu(savedDatHangEntity.getGhiChu())
            .trangThai(savedDatHangEntity.getTrangThai().name())
            .build();
    }

    @Override
    public void xoaDatHang(Long id) {
        DatHang dh = datHangRepository.findById(id)
            .orElseThrow(() -> new EntityNotExistedException("Không tồn tại đơn đặt hàng với id: " + id));

        dh.setDaXoa(true);
        datHangRepository.save(dh);
    }
}
