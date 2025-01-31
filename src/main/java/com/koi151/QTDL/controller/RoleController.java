package com.koi151.QTDL.controller;

import com.koi151.QTDL.mapper.ResponseDataMapper;
import com.koi151.QTDL.model.request.create.RoleCreateRequest;
import com.koi151.QTDL.model.request.update.RoleUpdateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final ResponseDataMapper responseDataMapper;
    private static final int MAX_PAGE_SIZE = 20;

    @GetMapping("/")
    public ResponseEntity<ResponseData> findRoles (
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "10") int limit
    ) {
        int pageSize = Math.min(limit, MAX_PAGE_SIZE);
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        var pages = roleService.findRoles(pageable);

        ResponseData responseData = responseDataMapper.toResponseData(pages, page, pageSize);
        responseData.setDesc(pages.isEmpty()
            ? "Không tìm thấy vai trò quản trị"
            : "Lấy dữ liệu các vai trò quản trị thành công");

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseData> createRole(
        @RequestBody @Valid RoleCreateRequest request
    ) {
        var accountCreated = roleService.createRole(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo thành công vai trò " + request.getRoleName())
                .build()
            , HttpStatus.CREATED);
    }

    @PatchMapping("/{roleId}")
    public ResponseEntity<ResponseData> updateRole(@PathVariable Long roleId,
                                                   @Valid @RequestBody RoleUpdateRequest request) {
        var updatedRole = roleService.updateRole(roleId, request);
        return ResponseEntity.ok(ResponseData.builder()
            .data(updatedRole)
            .desc("Cập nhật thành công thông tin vai trò ' " + updatedRole.getRoleName() +" '")
            .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteRole (@PathVariable(name = "id") Long id ) {
        roleService.deleteRole(id);

        return new ResponseEntity<>(
            ResponseData.builder()
                .desc("Xóa thành công vai trò quản trị với id: " + id)
                .build()
            , HttpStatus.OK);
    }
}
