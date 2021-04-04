package com.example.warehouseinventoryservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The type Article.
 */
@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "Articles")
public class Article {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("art_id")
    @Id
    private Long id;

    @JsonProperty("name")
    private String articleName;
    private Long stock;
}
