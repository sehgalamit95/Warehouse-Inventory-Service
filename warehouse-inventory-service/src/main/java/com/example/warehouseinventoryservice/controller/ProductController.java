package com.example.warehouseinventoryservice.controller;

import com.example.warehouseinventoryservice.dto.ErrorDetails;
import com.example.warehouseinventoryservice.dto.ProductDto;
import com.example.warehouseinventoryservice.exception.ValidationException;
import com.example.warehouseinventoryservice.model.Article;
import com.example.warehouseinventoryservice.model.Product;
import com.example.warehouseinventoryservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "List all products from repository")
    @ApiResponse(responseCode = "200", description = "Successful operation")
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
    @Operation(summary = "Add products to repository")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully add products"),
            @ApiResponse(responseCode = "400", description = "Invalid input/No Article in Stock",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    })
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
    @Operation(summary = "Sell products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully product with given id"),
            @ApiResponse(responseCode = "400", description = "Product out of Stock",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    })
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
    @Operation(summary = "List products in stock")
    @ApiResponse(responseCode = "200", description = "Successfully return list of products and respective stock")

    @GetMapping(value = "/inventory",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getInventoryInfo()
    {
        return productService.getInventoryInfo();
    }
}
