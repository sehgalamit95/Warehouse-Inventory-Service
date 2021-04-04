package com.example.warehouseinventoryservice.repository;

import com.example.warehouseinventoryservice.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Article repository.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
