package com.example.warehouseinventoryservice.service;

import com.example.warehouseinventoryservice.dto.ProductArticleDto;
import com.example.warehouseinventoryservice.dto.ProductDto;
import com.example.warehouseinventoryservice.exception.ValidationException;
import com.example.warehouseinventoryservice.model.Article;
import com.example.warehouseinventoryservice.model.Product;
import com.example.warehouseinventoryservice.model.ProductArticle;
import com.example.warehouseinventoryservice.repository.ArticleRepository;
import com.example.warehouseinventoryservice.repository.ProductArticleRepository;
import com.example.warehouseinventoryservice.repository.ProductRepository;
import com.example.warehouseinventoryservice.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
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
                .name("Dinning Table")
                .productArticles(List.of(productArticleDto1, productArticleDto2))
                .build();

        this.productDto2 = ProductDto.builder()
                .id(2L)
                .name("Dinning Chair")
                .productArticles(List.of(productArticleDto1))
                .build();

        this.article1 = Article.builder().id(1l).articleName("leg").stock(12L).build();
        this.article2 = Article.builder().id(2l).articleName("screw").stock(16L).build();

        this.product1 = Product.builder().id(1L).name("Dinning Table").build();
        this.product2 = Product.builder().id(2L).name("Dinning Chair").build();

        this.productArticle1 = ProductArticle.builder().id(1L).product(product1).article(article1).quantity(4L).build();
        this.productArticle2 = ProductArticle.builder().id(2L).product(product1).article(article2).quantity(2L).build();
        this.productArticle3 = ProductArticle.builder().id(3L).product(product2).article(article1).quantity(4L).build();


    }


    /**
     * Gets products test.
     */
    @Test
    @DisplayName("Test to check empty input")
    public void getProductsTest() {
        when(productRepository.findAll()).thenReturn(List.of());
        List<Product> products = productService.getProducts();
        assertTrue(products.isEmpty());
    }


    /**
     * Add product test.
     *
     * @throws ValidationException the validation exception
     */
    @Test
    @DisplayName("When no Article present, addProduct throws Validation Error")
    public void addProductTest() throws ValidationException {
        List<ProductDto> productDtos=new ArrayList<>();
        productDtos.add(productDto1);
        productDtos.add(productDto2);

        assertThrows(ValidationException.class,() -> productService.addProducts(productDtos));

    }


    /**
     * Test process products articles found.
     */
    @Test
    @DisplayName("When Article present ,no exception should be thrown")
    public void testProcessProducts_ArticlesFound() {
        List<ProductDto> productDtos =List.of(productDto1,productDto2);
        // Given
        given(articleRepository.findById(productArticleDto1.getArticleId())).willReturn(Optional.of(article1));
        given(articleRepository.findById(productArticleDto2.getArticleId())).willReturn(Optional.of(article2));

        assertDoesNotThrow(() -> productService.addProducts(productDtos));
    }


    /**
     * Test process products success.
     *
     * @throws Exception the exception
     */
    @Test
    @DisplayName("Successfully add the products")
    public void testProcessProducts_Success() throws Exception {
        List<ProductDto> productDtos = List.of(productDto1, productDto2);
        // Given
        given(articleRepository.findById(productArticleDto1.getArticleId())).willReturn(Optional.of(article1));
        given(articleRepository.findById(productArticleDto2.getArticleId())).willReturn(Optional.of(article2));

        assertDoesNotThrow(() -> productService.addProducts(productDtos));
        assertThat(productService.addProducts(productDtos)).isNotEmpty().hasSize(2);
    }


    /**
     * Gets stock test.
     */
    @Test
    @DisplayName("Calculate product's stock")
    public void getStockTest() {
        // Given
        given(productArticleRepository.findAll()).willReturn(List.of(productArticle1, productArticle2, productArticle3));

        // When
        List<Product> products = productService.getInventoryInfo();

        // then
        assertThat(products)
                .isNotEmpty()
                .hasSize(2)
                .extracting(Product::getName, Product::getStock)
                .containsExactlyInAnyOrder(
                        tuple("Dinning Chair", 3L),
                        tuple("Dinning Table", 3L)
                );
    }


    /**
     * Gets stock empty test.
     */
    @Test
    @DisplayName("Calculate product's stock when empty")
    public void getStockEmptyTest() {
        // Given
        given(productArticleRepository.findAll()).willReturn(List.of());

        // When
        List<Product> products = productService.getInventoryInfo();

        // then
        assertThat(products).isEmpty();
    }


    /**
     * Sell product test.
     *
     * @throws ValidationException the validation exception
     */
    @Test
    @DisplayName("Sell Product and update the inventory and throw no Exception")
    public void sellProductTest() throws ValidationException {
        // Given
        given(productRepository.findById(product1.getId())).willReturn(Optional.of(product1));
        // When
        Product products = productService.sellProduct(1L);
        // then
        assertDoesNotThrow(() -> productService.sellProduct(1L));
    }


    /**
     * Sell product exception test.
     */
    @Test
    @DisplayName("When no product found, should throw ValidationException")
    public void sellProductExceptionTest() {
        // Given
        given(productRepository.findById(product1.getId())).willReturn(Optional.empty());

        assertThrows(ValidationException.class, () -> productService.sellProduct(1L));
    }

    /**
     * Sell product less article exception test.
     */
    @Test
    @DisplayName("When articles not found, should throw ValidationException")
    public void sellProductLessArticleExceptionTest() {
        // Given
        productArticle3.setQuantity(14L);
        given(productRepository.findById(product2.getId())).willReturn(Optional.of(product2));
        given(productArticleRepository.findAllByProduct(product2)).willReturn(List.of(productArticle3));

        assertThrows(ValidationException.class, () -> productService.sellProduct(2L));
    }
}
