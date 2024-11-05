package com.koi151.QTDL.model.request.create;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgencyCreateRequest {

    @Size(max = 100, message = "Tên đại lý không vượt quá {max} kí tự")
    private String agencyName;

    @Size(max = 100, message = "Thông tin địa chỉ không vượt quá {max} kí tự")
    private String address;

    @Size(min = 10, max = 20, message = "Số điện thoại không hợp lệ")
    @Pattern(regexp = "^[0-9]+$", message = "Số điện thoại không hợp lệ")
    private String phone;

    @Size(max = 100, message = "Thông tin người đại diện không vượt quá {max} kí tự")
    private String repInfo;
}
