package com.koi151.QTDL.specification;


import com.koi151.QTDL.entity.view.ProductDetailsView;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
public class ProductDetailsSpecification {

    public static Specification<ProductDetailsView> hasProductId(Long productId) {
        return (root, query, criteriaBuilder) ->
            productId == null ? null : criteriaBuilder.equal(root.get("productId"), productId);
    }

    public static Specification<ProductDetailsView> hasProductNameLike(String productName) {
        return (root, query, criteriaBuilder) ->
            productName == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")), "%" + productName.toLowerCase() + "%");
    }

    public static Specification<ProductDetailsView> hasCategoryNameLike(String categoryName) {
        return (root, query, criteriaBuilder) ->
            categoryName == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("categoryName")), "%" + categoryName.toLowerCase() + "%");
    }

    public static Specification<ProductDetailsView> hasSupplierNameLike(String supplierName) {
        return (root, query, criteriaBuilder) ->
            supplierName == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("supplierName")), "%" + supplierName.toLowerCase() + "%");
    }

    public static Specification<ProductDetailsView> hasPriceEqualTo(BigDecimal price) {
        return (root, query, criteriaBuilder) ->
            price == null ? null : criteriaBuilder.equal(root.get("price"), price);
    }

    public static Specification<ProductDetailsView> hasPriceGreaterThanOrEqualTo(BigDecimal minPrice) {
        return (root, query, criteriaBuilder) ->
            minPrice == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<ProductDetailsView> hasPriceLessThanOrEqualTo(BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) ->
            maxPrice == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}