package com.koi151.QTDL.model.request.create;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeCreateRequest {

    @Size(min = 5, max = 100, message = "Họ và tên không hợp lệ")
    @NotBlank(message = "Họ và tên nhân viên không được để trống")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Họ và tên không được chứa số hoặc kí tự đặc biệt")
    private String fullName;

    private Long roleId = 1L;

    @Email(message = "Email không hợp lệ")
    @Size(max = 100, message = "Email không được vượt quá {max} kí tự")
    private String email;

    @NotBlank(message = "Thiếu số điện thoại")
    @Size(max = 100, message = "Số điện thoại không được vượt quá {max} kí tự")
    private String phone;

    @NotBlank(message = "Mật khẩu không hợp lệ")
    @Size(min = 4, max = 100, message = "Mật khẩu phải có tối thiểu {min} và tối đa {max} kí tự")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*]).*$",
        message = "Mật khẩu cần có ít nhất một kí tự in hoa và kí tự đặc biệt")
    private String password;

    @NotBlank(message = "Cần xác nhận lại mật khẩu")
    private String retypePassword;
}