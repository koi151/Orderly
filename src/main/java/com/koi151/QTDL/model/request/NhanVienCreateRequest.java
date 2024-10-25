package com.koi151.QTDL.model.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhanVienCreateRequest {

    @Size(min = 5, max = 100, message = "Ho va ten phai trong khoang {min} den {max} ki tu")
    @NotBlank(message = "Thieu ho ten")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Ho ten khong duoc chua so hoac ky tu dac biet")
    private String hoTen;

    @Email(message = "Email khong hop le")
    @Size(max = 100, message = "Email khong duoc vuot qua {max} ki tu")
    private String email;

    @NotBlank(message = "Thieu so dien thoai")
    @Size(max = 100, message = "So dien thoai khong duoc vuot qua {max} ki tu")
    private String sdt;

    @NotBlank(message = "Mat khau khong hop le")
    @Size(min = 4, max = 100, message = "Do dai mat khau toi thieu {min} va toi da {max} ki tu")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*]).*$",
        message = "Mat khau can co it nhat mot ki tu in hoa va ki tu dac biet")
    private String password;

    @NotBlank(message = "Can xac nhan lai mat khau")
    private String retypePassword;
}