package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.Product;
import com.koi151.QTDL.entity.view.ProductDetailsView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
                                    , JpaSpecificationExecutor<ProductDetailsView>
{
    Boolean existsByProductName(String name);

    Boolean existsByProductNameAndProductIdNot(String name, Long id);
    Optional<Product> findByProductIdAndDeleted(Long id, Boolean deleted);

    @Procedure(procedureName = "createProduct")
    void createProduct(
        @Param("p_category_id") Long categoryId,
        @Param("p_supplier_id") Long supplierId,
        @Param("p_product_name") String productName,
        @Param("p_price") BigDecimal price
    );

    @Procedure(procedureName = "updateProduct")
    void updateProduct(
        @Param("p_product_id") Long productId,
        @Param("p_category_id") Long categoryId,
        @Param("p_supplier_id") Long supplierId,
        @Param("p_product_name") String productName,
        @Param("p_price") BigDecimal price
    );
}
