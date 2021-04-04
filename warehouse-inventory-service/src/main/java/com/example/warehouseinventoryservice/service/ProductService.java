package com.example.warehouseinventoryservice.service;

import com.example.warehouseinventoryservice.dto.ProductDto;
import com.example.warehouseinventoryservice.model.Product;

import java.util.List;

/**
 * The interface Product service.
 */
public interface ProductService {
    /**
     * Gets products.
     *
     * @return the products
     */
    List<Product> getProducts();

    /**
     * Add products list.
     *
     * @param products the products
     * @return the list
     */
    List<Product> addProducts(List<ProductDto> products);

    /**
     * Add product product.
     *
     * @param productDto the product dto
     * @return the product
     */
    Product addProduct(ProductDto productDto);

    /**
     * Sell product product.
     *
     * @param id the id
     * @return the product
     */
    Product sellProduct(Long id);

    /**
     * Gets inventory info.
     *
     * @return the inventory info
     */
    List<Product> getInventoryInfo();
}
