package com.koi151.QTDL.model.request.search;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSearchRequest {

    private Long productId;

    @Size(max = 100, message = "Tên sản phẩm không quá {max} kí tự")
    private String productName;

    @Size(max = 100, message = "Tên danh mục sản phẩm không quá {max} kí tự")
    private String categoryName;

    @Size(max = 100, message = "Tên nhà cung cấp không quá {max} kí tự")
    private String supplierName;

    @DecimalMax(value = "1000000000", message = "Giá không được vượt quá {value}")
    private BigDecimal price;

    @PositiveOrZero(message = "Yêu cầu tìm kiếm giá tối thiểu không hợp lệ")
    private BigDecimal minPrice;

    @DecimalMax(value = "1000000000", message = "Giá tối đa không được vượt quá {value}")
    private BigDecimal maxPrice;

    @Pattern(regexp = "created_at|product_name|price", message = "Trường sắp xếp không hợp lệ")
    private String sortField = "created_at";

    @Pattern(regexp = "ASC|DESC", message = "Thứ tự sắp xếp phải là ASC hoặc DESC")
    private String sortDirection = "ASC";
}