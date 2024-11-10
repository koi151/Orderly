package com.koi151.QTDL.controller;

import com.koi151.QTDL.mapper.ResponseDataMapper;
import com.koi151.QTDL.model.request.create.AgencyCreateRequest;
import com.koi151.QTDL.model.request.update.AgencyUpdateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.AgencyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agencies")
public class AgencyController {

    private final AgencyService agencyService;
    private final ResponseDataMapper responseDataMapper;
    private static final int MAX_PAGE_SIZE = 20;

    @GetMapping("/")
    public ResponseEntity<ResponseData> findAgencies (
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "10") int limit
    ) {
        int pageSize = Math.min(limit, MAX_PAGE_SIZE);
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        var pages = agencyService.findAgencies(pageable);

        ResponseData responseData = responseDataMapper.toResponseData(pages, page, pageSize);
        responseData.setDesc(pages.isEmpty()
            ? "Không tìm thấy đại lý"
            : "Lấy dữ liệu các đại lý thành công");

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseData> createAgency(
        @RequestBody @Valid AgencyCreateRequest request
    ) {
        var accountCreated = agencyService.createAgency(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo thành công đại lý " + request.getAgencyName())
                .build()
            , HttpStatus.CREATED);
    }

    @PatchMapping("/{agencyId}")
    public ResponseEntity<ResponseData> updateAgency(@PathVariable Long agencyId,
                                                     @Valid @RequestBody AgencyUpdateRequest request) {
        var updatedCategory = agencyService.updateAgency(agencyId, request);
        return ResponseEntity.ok(ResponseData.builder()
            .data(updatedCategory)
            .desc("Cập nhật thành công thông tin nhà cung cấp ' " + updatedCategory.getAgencyId() + " '")
            .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteAgency (@PathVariable(name = "id") Long id ) {
        agencyService.deleteAgency(id);

        return new ResponseEntity<>(
            ResponseData.builder()
                .desc("Xóa thành công đại lý với id: " + id)
                .build()
            , HttpStatus.OK);
    }
}
