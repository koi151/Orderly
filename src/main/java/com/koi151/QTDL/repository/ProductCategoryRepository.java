package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Boolean existsByCategoryName(String name);

    @Query("SELECT COUNT(p) FROM product p WHERE p.productCategory.categoryId = :categoryId")
    Long countProductsByCategoryId (@Param("categoryId") Long categoryId);
}
