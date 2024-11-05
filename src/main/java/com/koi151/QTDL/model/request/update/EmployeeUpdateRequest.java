package com.koi151.QTDL.model.request.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeUpdateRequest {

    @Size(min = 5, max = 100, message = "Họ và tên không hợp lệ")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Họ và tên không được chứa số hoặc kí tự đặc biệt")
    private String fullName;

    private Long roleId;

    @Email(message = "Email không hợp lệ")
    @Size(max = 100, message = "Email không được vượt quá {max} kí tự")
    private String email;

    @Size(max = 100, message = "Số điện thoại không được vượt quá {max} kkí tự")
    private String phone;

    @Size(min = 4, max = 100, message = "Mật khẩu phải có tối thiểu {min} và tối đa {max} kí tự")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*]).*$",
        message = "Mật khẩu cần có ít nhất một kí tự in hoa và kí tự đặc biệt")
    private String password;
}