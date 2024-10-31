package com.koi151.QTDL.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierCreateDTO {
    private Long supplierId;
    private String supplierName;
    private String address;
    private String repInfo;
}
