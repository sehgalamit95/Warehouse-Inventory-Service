package com.example.warehouseinventoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The type Product article.
 */
@Data
@Entity
@Table(name = "ProductArticles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Product product;
    @ManyToOne
    private Article article;
    private Long quantity;
}
