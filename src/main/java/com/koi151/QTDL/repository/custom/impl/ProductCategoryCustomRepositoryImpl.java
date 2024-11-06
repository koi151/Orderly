package com.koi151.QTDL.repository.custom.impl;


import com.koi151.QTDL.entity.ProductCategory;
import com.koi151.QTDL.model.request.search.ProductCategorySearchRequest;
import com.koi151.QTDL.repository.custom.ProductCategoryCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductCategoryCustomRepositoryImpl implements ProductCategoryCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private List<Predicate> buildPredicates(ProductCategorySearchRequest request, Root<ProductCategory> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (request.getCategoryName() != null && !request.getCategoryName().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("categoryName")), "%" + request.getCategoryName().toLowerCase() + "%"));
        }

        // default
        predicates.add(cb.equal(root.get("deleted"), false));

        return predicates;
    }

    @Override
    public Page<ProductCategory> searchCategories(ProductCategorySearchRequest request, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductCategory> query = cb.createQuery(ProductCategory.class);
        Root<ProductCategory> root = query.from(ProductCategory.class);

        List<Predicate> predicates = buildPredicates(request, root, cb);

        query.where(predicates.toArray(new Predicate[0]));

        // default
        query.orderBy(cb.desc(root.get("createdAt")));

        // Perform query with pagination
        List<ProductCategory> categories = entityManager.createQuery(query)
            .setFirstResult((int) pageable.getOffset())
            .setMaxResults(pageable.getPageSize())
            .getResultList();

        // count total records
        long total = getTotalCount(request, cb);

        return new PageImpl<>(categories, pageable, total);
    }

    private long getTotalCount(ProductCategorySearchRequest request, CriteriaBuilder cb) {
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<ProductCategory> root = countQuery.from(ProductCategory.class);
        countQuery.select(cb.count(root));

        List<Predicate> predicates = buildPredicates(request, root, cb);
        countQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
