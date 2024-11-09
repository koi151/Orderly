package com.koi151.QTDL.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SupplierDTO {
    private Long supplierId;
    private String supplierName;
    private String address;
    private String repInfo;
}
