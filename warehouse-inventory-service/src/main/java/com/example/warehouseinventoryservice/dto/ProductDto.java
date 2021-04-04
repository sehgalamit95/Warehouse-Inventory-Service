package com.example.warehouseinventoryservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * The type Product dto.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {
    private Long id;

    private String name;

    @JsonProperty("contain_articles")
    @SerializedName("contain_articles")
    private List<ProductArticleDto> productArticles;

}
