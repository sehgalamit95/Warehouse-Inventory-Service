package com.example.warehouseinventoryservice.service.impl;

import com.example.warehouseinventoryservice.dto.ArticleDto;
import com.example.warehouseinventoryservice.exception.ValidationException;
import com.example.warehouseinventoryservice.model.Article;

import com.example.warehouseinventoryservice.repository.ArticleRepository;
import com.example.warehouseinventoryservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Article service.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;


    /**
     * Instantiates a new Article service.
     *
     * @param articleRepository the article repository
     */
    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> addArticles(List<ArticleDto> articleDtos) throws ValidationException {
        List<Article> articles = new ArrayList<>();
        for (ArticleDto articleDto : articleDtos) {
            Article article = this.addArticle(articleDto);
            articles.add(article);
        }
        return articles;
    }

    private Article addArticle(ArticleDto articleDto) throws ValidationException {

        Article article = articleRepository.findById(articleDto.getArticleId())
                .orElse(Article.builder().id(articleDto.getArticleId()).build());
        article.setArticleName(articleDto.getName());
        article.setStock(articleDto.getStock());
        return articleRepository.save(article);
    }
}
