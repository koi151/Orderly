package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.RoleCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vai-tro")
public class RoleController {

    private final RoleService roleService;

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
