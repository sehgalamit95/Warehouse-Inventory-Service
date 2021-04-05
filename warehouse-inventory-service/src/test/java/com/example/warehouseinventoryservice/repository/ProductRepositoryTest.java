package com.example.warehouseinventoryservice.repository;

import com.example.warehouseinventoryservice.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Product repository test.
 */
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    /**
     * Find all test.
     */

    @Test
    @DisplayName("Test if findAll method returns empty")
    public void findAllTest()
    {
        List<Product> products=productRepository.findAll();
        assertTrue(products.isEmpty());
    }

}
