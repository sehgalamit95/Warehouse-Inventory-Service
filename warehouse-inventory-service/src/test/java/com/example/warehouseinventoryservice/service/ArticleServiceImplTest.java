package com.example.warehouseinventoryservice.service;

import com.example.warehouseinventoryservice.dto.ArticleDto;
import com.example.warehouseinventoryservice.exception.ValidationException;
import com.example.warehouseinventoryservice.model.Article;
import com.example.warehouseinventoryservice.repository.ArticleRepository;
import com.example.warehouseinventoryservice.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * The type Article service impl test.
 */
@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {
    @Mock
    private ArticleRepository articleRepository;

    private ArticleService articleService;

    private List<ArticleDto> articles;

    private ArticleDto articleDto1;

    private ArticleDto articleDto2;

    /**
     * Sets .
     */
    @BeforeEach
    public void setup() {
        this.articleService = new ArticleServiceImpl(articleRepository);
        this.articles = new ArrayList<>();

        articleDto1 = ArticleDto.builder().articleId(1l).name("leg").stock(12L).build();
        articleDto2 = ArticleDto.builder().articleId(2l).name("screw").stock(16L).build();

        articles.add(articleDto1);
        articles.add(articleDto2);

    }

    /**
     * Gets articles test.
     */
    @Test
    public void getArticlesTest() {
        when(articleRepository.findAll()).thenReturn(List.of());
        List<Article> articles = articleService.getArticles();
        assertTrue(articles.isEmpty());
    }


    /**
     * Add articles test.
     */
    @Test
    @DisplayName("Test to check storage of Articles")
    public void addArticlesTest() throws ValidationException {
        Article article1 = Article.builder().id(1l).articleName("leg").stock(12L).build();
        Article article2 = Article.builder().id(2l).articleName("screw").stock(16L).build();
        when(articleRepository.findById(articleDto1.getArticleId())).thenReturn(Optional.of(article1));
        when(articleRepository.findById(articleDto2.getArticleId())).thenReturn(Optional.of(article2));

        List<Article> articles = articleService.addArticles(this.articles);
        assertFalse(articles.isEmpty(),"Articles are not getting stored");

    }
}
