package com.example.warehouseinventoryservice.service;

import com.example.warehouseinventoryservice.dto.ArticleDto;
import com.example.warehouseinventoryservice.model.Article;

import java.util.List;

/**
 * The interface Article service.
 */
public interface ArticleService {
    /**
     * Gets articles.
     *
     * @return the articles
     */
    List<Article> getArticles();

    /**
     * Add articles list.
     *
     * @param articles the articles
     * @return the list
     */
    List<Article> addArticles(List<ArticleDto> articles);
}
