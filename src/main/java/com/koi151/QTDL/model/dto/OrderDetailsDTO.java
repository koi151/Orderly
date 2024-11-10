package com.koi151.QTDL.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailsDTO {
    private long orderId;
    private String agencyName;
    private String employeeName;
    private String phone;
    private String notes;
    private String status;
}
