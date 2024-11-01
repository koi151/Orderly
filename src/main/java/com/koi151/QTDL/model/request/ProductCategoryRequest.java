package com.koi151.QTDL.model.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryRequest {

    @Size(max = 100, message = "Tên loại sản phẩm không vượt quá {max} kí tự")
    private String categoryName;

    @Size(max = 65535, message = "Mô tả danh mục không vượt quá {max} kí tự")
    private String description;
}
