package com.koi151.QTDL.model.request.update;

import com.koi151.QTDL.model.request.OrderDetailRequest;
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
public class OrderUpdateRequest {

    @NotNull(message = "Yêu cầu đặt hàng cần có mã đại lý")
    private Long agencyId;

    @NotNull(message = "Yêu cầu đặt hàng cần có mã mã nhân viên")
    private Long employeeId;

    @Valid
    private List<OrderDetailRequest> orderDetails;

    @Size(max = 65535, message = "Thông tin ghi chú không vượt quá {max} kí tự")
    private String notes;
}
