package com.koi151.QTDL.model.request;

import com.koi151.QTDL.entity.NhanVien;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VaiTroRequest {

    @Size(min = 3, max = 100, message = "Ten vai tro phai trong khoang {min} den {max} ki tu")
    private String tenVT;

    @Size(max = 65535, message = "Do dai mo ta khong vuot qua {max} ki tu")
    private String moTa;
}