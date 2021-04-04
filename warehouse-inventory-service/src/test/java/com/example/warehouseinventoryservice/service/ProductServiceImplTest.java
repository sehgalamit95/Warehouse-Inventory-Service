package com.example.warehouseinventoryservice.service;

import com.example.warehouseinventoryservice.dto.ProductArticleDto;
import com.example.warehouseinventoryservice.dto.ProductDto;
import com.example.warehouseinventoryservice.model.Article;
import com.example.warehouseinventoryservice.model.Product;
import com.example.warehouseinventoryservice.model.ProductArticle;
import com.example.warehouseinventoryservice.repository.ArticleRepository;
import com.example.warehouseinventoryservice.repository.ProductArticleRepository;
import com.example.warehouseinventoryservice.repository.ProductRepository;
import com.example.warehouseinventoryservice.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * The type Product service impl test.
 */
@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ProductArticleRepository productArticleRepository;

    private ProductService productService;


    private List<Product> products;

    //==============
    private Article article1;
    private Article article2;

    private ProductArticle productArticle1;
    private ProductArticle productArticle2;
    private ProductArticle productArticle3;

    private Product product1;
    private Product product2;

    private ProductDto productDto1;
    private ProductDto productDto2;

    private ProductArticleDto productArticleDto1;
    private ProductArticleDto productArticleDto2;

    /**
     * Sets .
     */
    @BeforeEach
    public void setup() {

        productService = new ProductServiceImpl(productRepository, articleRepository, productArticleRepository);

        this.productArticleDto1 = ProductArticleDto.builder().articleId(1L).quantity(4L).build();
        this.productArticleDto2 = ProductArticleDto.builder().articleId(2L).quantity(2L).build();


        this.productDto1 = ProductDto.builder()
                .id(1L)
                .productArticles(List.of(productArticleDto1, productArticleDto2))
                .build();

        this.productDto1 = ProductDto.builder()
                .id(1L)
                .productArticles(List.of(productArticleDto1))
                .build();

        this.article1 = Article.builder().id(1l).articleName("leg").stock(12L).build();
        this.article2 = Article.builder().id(2l).articleName("screw").stock(16L).build();

        this.product1 = Product.builder().id(1L).name("Dinning Table").build();
        this.product2 = Product.builder().id(2L).name("Dinning Chair").build();

        this.productArticle1 = ProductArticle.builder().id(1L).product(product1).article(article1).build();
        this.productArticle2 = ProductArticle.builder().id(2L).product(product1).article(article2).build();
        this.productArticle3 = ProductArticle.builder().id(3L).product(product2).article(article1).build();


    }

    /**
     * Gets products test.
     */
    @Test
    public void getProductsTest() {
        when(productRepository.findAll()).thenReturn(List.of());
        List<Product> products = productService.getProducts();
        assertTrue(products.isEmpty());
    }

    /**
     * Add product test.
     */
    @Test
    @Disabled
    public void addProductTest() {
        List<ProductDto> productDtos= List.of(productDto1,productDto2);
     //   when(productRepository.save(product)).thenReturn(this.products);
        //List<Product> products = productService.addProducts(this.products);
        Assertions.assertThat(products).containsExactlyInAnyOrder(product1, product2);
    }
}
