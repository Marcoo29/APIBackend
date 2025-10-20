package com.uade.tpo.ecommerce.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.ProductRequest;
import com.uade.tpo.ecommerce.exceptions.ProductDuplicateException;
import com.uade.tpo.ecommerce.service.inter.ProductService;

@RestController
@RequestMapping("products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<Product> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "name-asc") String sort,
            @RequestParam(required = false) String searchTerm) {

        return productService.getProducts(page, size, sort, searchTerm);
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductRequest productRequest) throws ProductDuplicateException {
        return productService.createProduct(productRequest);
    }

    @PutMapping("/{productId}/update")
    public Product updateProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productId, productRequest);
    }

    @DeleteMapping("/{productId}/delete")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
