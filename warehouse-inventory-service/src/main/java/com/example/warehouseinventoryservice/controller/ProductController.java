package com.example.warehouseinventoryservice.controller;

import com.example.warehouseinventoryservice.dto.ProductDto;
import com.example.warehouseinventoryservice.exception.ValidationException;
import com.example.warehouseinventoryservice.model.Article;
import com.example.warehouseinventoryservice.model.Product;
import com.example.warehouseinventoryservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * The type Product controller.
 */
@RestController
    @RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Instantiates a new Product controller.
     *
     * @param productService the product service
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets products.
     *
     * @return the products
     */
    @GetMapping
    public List<Product> getProducts()
    {
        return productService.getProducts();
    }

    /**
     * Add products list.
     *
     * @param productRequest the product request
     * @return the list
     * @throws ValidationException the validation exception
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> addProducts(@RequestBody Map<String,List<ProductDto>> productRequest) throws ValidationException {
        List<ProductDto> products = productRequest.get("products");
       return productService.addProducts(products);
    }

    /**
     * Sell product product.
     *
     * @param id the id
     * @return the product
     * @throws ValidationException the validation exception
     */
    @PutMapping(value = "/sell/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Product sellProduct(@PathVariable Long id) throws ValidationException
    {
        return productService.sellProduct(id);
    }

    /**
     * Gets inventory info.
     *
     * @return the inventory info
     */
    @GetMapping(value = "/inventory",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getInventoryInfo()
    {
        return productService.getInventoryInfo();
    }
}
