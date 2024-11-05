package com.koi151.QTDL.model.request.update;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleUpdateRequest {

    @Size(max = 100, message = "Tên vai trò quản trị không vượt quá {max} kí tự")
    private String roleName;

    @Size(max = 65535, message = "Mô tả không được vượt quá {max} kí tự")
    private String description;
}