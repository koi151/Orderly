package com.koi151.QTDL.model.request;

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
public class EmployeeLoginRequest {

    @NotBlank(message = "Mật khẩu không được trống")
    @Size(min = 4, max = 100, message = "Mật khẩu phải có tối thiểu {min} và tối đa {max} kí tự")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*]).*$",
        message = "Mật khẩu cần có ít nhất một kí tự in hoa và kí tự đặc biệt")
    private String password;

    @Email(message = "Email không hợp lệ")
    @Size(max = 100, message = "Email không được vượt quá {max} kí tự")
    private String email;
}
