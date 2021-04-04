package com.example.warehouseinventoryservice.controller;

import com.example.warehouseinventoryservice.dto.ArticleDto;
import com.example.warehouseinventoryservice.model.Article;
import com.example.warehouseinventoryservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * The type Article controller.
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    /**
     * Instantiates a new Article controller.
     *
     * @param articleService the article service
     */
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * Gets articles.
     *
     * @return the articles
     */
    @GetMapping
    public List<Article> getArticles()
    {
        return articleService.getArticles();
    }

    /**
     * Add articles list.
     *
     * @param articleRequest the article request
     * @return the list
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Article> addArticles(@RequestBody Map<String,List<ArticleDto>> articleRequest)
    {
        List<ArticleDto> data=articleRequest.get("inventory");
        return articleService.addArticles(data);
    }
}
