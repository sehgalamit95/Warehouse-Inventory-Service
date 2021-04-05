package com.example.warehouseinventoryservice.service.impl;

import com.example.warehouseinventoryservice.dto.ProductArticleDto;
import com.example.warehouseinventoryservice.dto.ProductDto;
import com.example.warehouseinventoryservice.exception.ValidationException;
import com.example.warehouseinventoryservice.model.Article;
import com.example.warehouseinventoryservice.model.Product;
import com.example.warehouseinventoryservice.model.ProductArticle;
import com.example.warehouseinventoryservice.repository.ArticleRepository;
import com.example.warehouseinventoryservice.repository.ProductArticleRepository;
import com.example.warehouseinventoryservice.repository.ProductRepository;
import com.example.warehouseinventoryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * The type Product service.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ArticleRepository articleRepository;
    private final ProductArticleRepository productArticleRepository;

    /**
     * Instantiates a new Product service.
     *
     * @param productRepository        the product repository
     * @param articleRepository        the article repository
     * @param productArticleRepository the product article repository
     */
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ArticleRepository articleRepository,
                              ProductArticleRepository productArticleRepository) {
        this.productRepository = productRepository;
        this.productArticleRepository = productArticleRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> addProducts(List<ProductDto> productDtos) throws ValidationException {
        List<Product> products = new ArrayList<>();
        for (ProductDto productDto : productDtos) {
            Product product = this.addProduct(productDto);
            products.add(product);
        }
        return products;
    }

    @Override
    @Transactional
    public Product addProduct(ProductDto productDto) throws ValidationException {
        Product product =Product.builder().name(productDto.getName()).build();
        List<ProductArticle> productArticles = new ArrayList<>();
        for (ProductArticleDto productArticleDto : productDto.getProductArticles()) {
            Article article = articleRepository.findById(productArticleDto.getArticleId())
                    .orElseThrow(() -> new ValidationException("Article not found!!"));
            ProductArticle productArticle = ProductArticle.builder()
                    .product(product)
                    .article(article)
                    .quantity(productArticleDto.getQuantity()).build();
            productArticles.add(productArticle);
        }
        productArticleRepository.saveAll(productArticles);
        return product;

    }

    @Override
    public Product sellProduct(Long id) throws ValidationException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Product Out of Stock"));
        List<ProductArticle> productArticles = productArticleRepository.findAllByProduct(product);
        for (ProductArticle productArticle : productArticles) {
            Article article = productArticle.getArticle();
            if (article.getStock() - productArticle.getQuantity() < 0)
                throw new ValidationException("Product parts are out of Stock");
            article.setStock(article.getStock() - productArticle.getQuantity());
            articleRepository.save(article);
        }
        return product;
    }


    private final BiFunction<Long, ProductArticle, Long> calculateStock = (intermediateResult, productArticle) -> {
        Article article = productArticle.getArticle();
        Long quantity = productArticle.getQuantity();

        return Math.min(intermediateResult, article.getStock() / quantity);
    };

    public List<Product> getInventoryInfo() {
        return productArticleRepository.findAll().stream()
                .collect(Collectors.groupingBy(ProductArticle::getProduct))
                .entrySet().stream()
                .map(entry -> {
                    Product product = entry.getKey();
                    Long stock = entry.getValue().stream()
                            .reduce(Long.MAX_VALUE, calculateStock, Math::min);
                    product.setStock(stock);
                    return product;
                }).collect(Collectors.toList());
    }
}
