package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Boolean existsByCategoryName(String name);

//    @Query("SELECT COUNT(sp) FROM product sp WHERE sp.category.categoryId = :categoryId")
//    Long (@Param("categoryId") Long categoryId);
}
