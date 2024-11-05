package com.koi151.QTDL.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO {
    private long employeeId;
    private String fullName;
    private String email;
    private String phone;
}
