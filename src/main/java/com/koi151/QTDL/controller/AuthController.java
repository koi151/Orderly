package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.EmployeeLoginRequest;
import com.koi151.QTDL.model.response.LoginResponseDTO;
import com.koi151.QTDL.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid EmployeeLoginRequest loginReq) {
        Optional<LoginResponseDTO> responseDTO = employeeService.login(loginReq);
        return ResponseEntity.ok(responseDTO);
    }
}
