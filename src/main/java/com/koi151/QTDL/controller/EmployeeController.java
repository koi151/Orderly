package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.EmployeeCreateRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<String> getEmployee() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/")
    public ResponseEntity<ResponseData> createAccount(
        @RequestBody @NotNull @Valid EmployeeCreateRequest request
    ) {
        var accountCreated = employeeService.createEmployee(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tạo thành công tài khoản nhân viên")
                .build()
            , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteNhanVien (@PathVariable(name = "id") Long id ) {
        employeeService.deleteEmployee(id);

        return new ResponseEntity<>(
            ResponseData.builder()
                .desc("Xóa thành công nhân viên với id: " + id)
                .build()
            , HttpStatus.OK);
    }
}

