package com.koi151.QTDL.controller;

import com.koi151.QTDL.mapper.ResponseDataMapper;
import com.koi151.QTDL.model.request.create.EmployeeCreateRequest;
import com.koi151.QTDL.model.request.search.ProductCategorySearchRequest;
import com.koi151.QTDL.model.request.update.AgencyUpdateRequest;
import com.koi151.QTDL.model.request.update.EmployeeUpdateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.EmployeeService;
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
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ResponseDataMapper responseDataMapper;
    private static final int MAX_PAGE_SIZE = 20;


    @GetMapping("/")
    public ResponseEntity<ResponseData> findEmployees (
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "10") int limit
    ) {
        int pageSize = Math.min(limit, MAX_PAGE_SIZE);
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        var propertiesPage = employeeService.findEmployees(pageable);

        ResponseData responseData = responseDataMapper.toResponseData(propertiesPage, page, pageSize);
        responseData.setDesc(propertiesPage.isEmpty()
            ? "Không tìm thấy nhân viên"
            : "Lấy dữ liệu các nhân viên thành công");

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseData> createAccount(
        @RequestBody @Valid EmployeeCreateRequest request
    ) {
        var accountCreated = employeeService.createEmployee(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo thành công tài khoản nhân viên")
                .build()
            , HttpStatus.OK);
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<ResponseData> updateEmployee(@PathVariable Long employeeId,
                                                     @Valid @RequestBody EmployeeUpdateRequest request) {
        var updatedCategory = employeeService.updateEmployee(employeeId, request);
        return ResponseEntity.ok(ResponseData.builder()
            .data(updatedCategory)
            .desc("Cập nhật thành công thông tin nhân viên với id: " + updatedCategory.getEmployeeId())
            .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteEmployee (@PathVariable(name = "id") Long id ) {
        employeeService.deleteEmployee(id);

        return new ResponseEntity<>(
            ResponseData.builder()
                .desc("Xóa thành công nhân viên với id: " + id)
                .build()
            , HttpStatus.OK);
    }
}

