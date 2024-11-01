package com.koi151.QTDL.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateRequest {

    @NotNull(message = "Yêu cầu đặt hàng cần có mã đại lý")
    private Long agencyId;

    @NotNull(message = "Yêu cầu đặt hàngcần có mã mã nhân viên")
    private Long employeeId;

    @Valid
    private List<OrderDetailRequest> orderDetails;

    @Size(max = 65535, message = "Thông tin ghi chú không vượt quá {max} kí tự")
    private String notes;
}
