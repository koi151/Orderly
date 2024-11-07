package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.ProductCategory;
import com.koi151.QTDL.repository.custom.ProductCategoryCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>,
                                                    ProductCategoryCustomRepository
{
    @Procedure(procedureName = "createCategory")
    Long createCategory(
        @Param("p_category_name") String categoryName,
        @Param("p_description") String description
    );

    @Procedure(procedureName = "updateCategory")
    void updateCategory(
        @Param("p_category_id") Long categoryId,
        @Param("p_category_name") String categoryName,
        @Param("p_description") String description
    );

    Boolean existsByCategoryName(String name);
    Boolean existsByCategoryNameAndCategoryIdNot(String name, Long id);
    @Query("SELECT COUNT(p) FROM product p WHERE p.productCategory.categoryId = :categoryId")
    Long countProductsByCategoryId (@Param("categoryId") Long categoryId);
    Optional<ProductCategory> findByCategoryIdAndDeleted(Long id, Boolean deleted);
}
