package com.example.warehouseinventoryservice.service;

import com.example.warehouseinventoryservice.dto.ProductDto;
import com.example.warehouseinventoryservice.exception.ValidationException;
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
     * @throws ValidationException the validation exception
     */
    List<Product> addProducts(List<ProductDto> products) throws ValidationException;

    /**
     * Add product product.
     *
     * @param productDto the product dto
     * @return the product
     * @throws ValidationException the validation exception
     */
    Product addProduct(ProductDto productDto) throws ValidationException;

    /**
     * Sell product product.
     *
     * @param id the id
     * @return the product
     * @throws ValidationException the validation exception
     */
    Product sellProduct(Long id) throws ValidationException;

    /**
     * Gets inventory info.
     *
     * @return the inventory info
     */
    List<Product> getInventoryInfo();
}
