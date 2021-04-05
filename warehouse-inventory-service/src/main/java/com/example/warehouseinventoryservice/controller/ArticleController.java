package com.example.warehouseinventoryservice.controller;

import com.example.warehouseinventoryservice.dto.ArticleDto;
import com.example.warehouseinventoryservice.dto.ErrorDetails;
import com.example.warehouseinventoryservice.exception.ValidationException;
import com.example.warehouseinventoryservice.model.Article;
import com.example.warehouseinventoryservice.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all Articles from repository")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping
    public List<Article> getArticles() {
        return articleService.getArticles();
    }


    /**
     * Add articles list.
     *
     * @param articleDtos the article dtos
     * @return the list
     * @throws ValidationException the validation exception
     */
    @Operation(summary = "Sell products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Articles added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    })
   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Article> addArticles(@RequestBody List<ArticleDto> articleDtos) throws ValidationException {
        return articleService.addArticles(articleDtos);
    }
}
