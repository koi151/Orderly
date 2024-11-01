package com.koi151.QTDL.model.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierRequest {

    @Size(max = 100, message = "Tên nhà cung cấp không vượt quá {max} kí tự")
    private String supplierName;

    @Size(max = 100, message = "Địa chỉ nhà cung cấp không vượt quá {max} kí tự")
    private String address;

    @Size(max = 65535, message = "Thông tin người đại diện không vượt quá {max} kí tự")
    private String repInfo;
}