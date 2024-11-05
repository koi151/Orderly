package com.koi151.QTDL.model.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleCreateRequest {

    @NotBlank(message = "Thiếu tên vai trò quản trị")
    @Size(max = 100, message = "Tên vai trò quản trị không vượt quá {max} kí tự")
    private String roleName;

    @Size(max = 65535, message = "Độ dài mô tả không vượt quá {max} kí tự")
    private String description;
}