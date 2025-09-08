package com.uade.tpo.ecommerce.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.ProductRequest;
import com.uade.tpo.ecommerce.exceptions.ProductDuplicateException;
import com.uade.tpo.ecommerce.service.inter.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(productService.getProducts(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(productService.getProducts(PageRequest.of(page, size)));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Optional<Product> result = productService.getProductById(productId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stock/{productId}")
    public ResponseEntity<Integer> getProductStockById(@PathVariable Long productId) {
    Optional<Product> result = productService.getProductById(productId);
    if (result.isPresent()) {
        return ResponseEntity.ok(result.get().getStock());
    }
    return ResponseEntity.notFound().build(); // 
}

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequest productRequest)
            throws ProductDuplicateException {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setManufacturer(productRequest.getManufacturer());
        product.setStock(productRequest.getStock());
        product.setDescription(productRequest.getDescription());
        product.setFitFor(productRequest.getFitFor());
        product.setProductStatus(productRequest.getStatus());
        Category category = categoryService.getCategoryById(productRequest.getCategoryId())
        .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        product.setCategory(category);


        Product result = productService.createProduct(product);

         return ResponseEntity.created(URI.create("/products/" + result.getId())).body(result);
    }


    @PutMapping
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        Optional<Product> result = productService.getProductById(productId);
        if (result.isPresent()) {
            productService.deleteProduct(productId);
        }
        return ResponseEntity.notFound().build(); // 
    }
}
