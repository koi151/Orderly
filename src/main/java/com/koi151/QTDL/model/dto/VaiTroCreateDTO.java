package com.koi151.QTDL.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaiTroCreateDTO {
    private long maVT;
    private String tenVT;
    private String moTa;
}
