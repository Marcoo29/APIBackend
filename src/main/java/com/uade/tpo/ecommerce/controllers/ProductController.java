package com.uade.tpo.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/by-category/{id}")
    public Page<Product> getProductsByCategory(
            @PathVariable("id") Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "name-asc") String sort,
            @RequestParam(required = false) String searchTerm) {
        return productService.getProductsByCategory(categoryId, page, size, sort, searchTerm);
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductRequest productRequest) throws ProductDuplicateException {
        return productService.createProduct(productRequest);
    }

    @PutMapping("/{productId}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Product updateProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productId, productRequest);
    }

    @DeleteMapping("/{productId}/delete")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        List<Product> all = productService.getAllProducts();
        return all;
    }

    @PutMapping("/{productId}/discount")
    @PreAuthorize("hasRole('ADMIN')")
    public Product setDiscount(
            @PathVariable Long productId,
            @RequestParam("discountPrice") float discountPrice) {

        return productService.setDiscountPrice(productId, discountPrice);
    }

    @DeleteMapping("/{productId}/discount")
    @PreAuthorize("hasRole('ADMIN')")
    public Product clearDiscount(@PathVariable Long productId) {
        return productService.clearDiscount(productId);
    }
}
