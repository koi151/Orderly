package com.koi151.QTDL.model.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryCreateRequest {

    @Size(max = 100, message = "Tên loại sản phẩm không vượt quá {max} kí tự")
    private String categoryName;
}
