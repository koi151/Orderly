package com.koi151.QTDL.model.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {

    @Size(min = 3, max = 100, message = "Ten vai tro phai trong khoang {min} den {max} ki tu")
    private String roleName;

    @Size(max = 65535, message = "Do dai mo ta khong vuot qua {max} ki tu")
    private String description;
}