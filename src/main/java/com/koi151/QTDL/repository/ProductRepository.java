package com.koi151.QTDL.repository;

import com.koi151.QTDL.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsByProductName(String name);

    Boolean existsByProductNameAndProductIdNot(String name, Long id);
    Optional<Product> findByProductIdAndDeleted(Long id, Boolean deleted);
}
