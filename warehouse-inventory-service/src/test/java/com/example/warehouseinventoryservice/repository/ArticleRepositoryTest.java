package com.example.warehouseinventoryservice.repository;

import com.example.warehouseinventoryservice.model.Article;
import com.example.warehouseinventoryservice.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Article repository test.
 */
@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    private List<Article> articles;

    private Article article1;

    private Article article2;

    /**
     * Sets .
     */
    @BeforeEach
    public void setup() {
        this.articles=new ArrayList<>();

        article1=new Article();
        article1.setId(Long.valueOf(1));
        article1.setArticleName("leg");
        article1.setStock(Long.valueOf(12));

        article2=new Article();
        article2.setId(Long.valueOf(2));
        article2.setArticleName("screw");
        article2.setStock(Long.valueOf(8));

        articles.add(article1);
        articles.add(article2);

    }

    /**
     * Save all test.
     */
    @Test
    public void saveAllTest() {
        List<Article> articles=articleRepository.saveAll(this.articles);
        Optional<Article> optional=articleRepository.findById(article1.getId());
        assertTrue(optional.isPresent());
        assertEquals(article1,optional.get());
    }
}