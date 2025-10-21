package com.uade.tpo.ecommerce.service.inter;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.ProductRequest;
import com.uade.tpo.ecommerce.exceptions.ProductDuplicateException;

public interface ProductService {
    Page<Product> getProducts(int page, int size, String sortOption, String searchTerm);

    Optional<Product> getProductById(Long productId);

    int getProductStockById(Long productId);

    Product createProduct(ProductRequest productRequest) throws ProductDuplicateException;

    Product updateProduct(Long productId, ProductRequest productRequest);

    void deleteProduct(Long productId);
}
