package com.example.warehouseinventoryservice.controller;

import com.example.warehouseinventoryservice.model.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.swagger.v3.core.util.Json;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Article controller test.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private List<Article> articles;

    private Article article1;

    private Article article2;

    private String json;
    @Autowired
    ObjectMapper objectMapper;

    /**
     * Sets .
     */
    @BeforeEach
    public void setup() throws JsonProcessingException {
        this.articles = new ArrayList<>();

        article1 = new Article();
        article1.setId(Long.valueOf(1));
        article1.setArticleName("leg");
        article1.setStock(Long.valueOf(12));

        article2 = new Article();
        article2.setId(Long.valueOf(2));
        article2.setArticleName("screw");
        article2.setStock(Long.valueOf(8));

        articles.add(article1);
        articles.add(article2);

        json=objectMapper.writeValueAsString(articles);

    }

    /**
     * Gets articles test.
     *
     * @throws Exception the exception
     */
    @Test
    public void getArticlesTest() throws Exception {
        mockMvc.perform(get("/articles"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Add articles test.
     *
     * @throws Exception the exception
     */
    @Test
    public void addArticlesTest() throws Exception {
        mockMvc.perform(post("/articles")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
