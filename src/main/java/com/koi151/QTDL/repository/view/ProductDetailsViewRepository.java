package com.koi151.QTDL.repository.view;

import com.koi151.QTDL.entity.view.ProductDetailsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsViewRepository extends JpaRepository<ProductDetailsView, Long>,
                                                JpaSpecificationExecutor<ProductDetailsView>
{

}