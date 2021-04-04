package com.example.warehouseinventoryservice.dto;

import com.example.warehouseinventoryservice.model.Article;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Article dto.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto implements Serializable {

    @JsonProperty("art_id")
    @SerializedName("art_id")
    private Long articleId;

    private String name;

    private Long stock;

}
