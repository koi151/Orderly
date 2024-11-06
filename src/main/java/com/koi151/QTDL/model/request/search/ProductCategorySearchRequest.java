package com.koi151.QTDL.model.request.search;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategorySearchRequest {

    @Size(max = 100, message = "Tên danh mục tìm kiếm không quá {max} kí tự")
    private String categoryName;
}
