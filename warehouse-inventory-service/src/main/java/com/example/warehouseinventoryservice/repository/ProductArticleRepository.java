package com.example.warehouseinventoryservice.repository;

import com.example.warehouseinventoryservice.model.Product;
import com.example.warehouseinventoryservice.model.ProductArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Product article repository.
 */
@Repository
public interface ProductArticleRepository extends JpaRepository<ProductArticle, Long> {

    /**
     * Find all by product list.
     *
     * @param product the product
     * @return the list
     */
    List<ProductArticle> findAllByProduct(Product product);
}
