package com.koi151.QTDL.model.request.update;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryUpdateRequest {

    @Size(max = 100, message = "Tên danh mục không quá {max} kí tự")
    private String categoryName;

    @Size(max = 65535, message = "Mô tả không vượt quá {max} kí tự")
    private String description;
}
