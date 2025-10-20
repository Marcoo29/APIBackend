package com.uade.tpo.ecommerce.service.inter;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.ProductRequest;
import com.uade.tpo.ecommerce.exceptions.ProductDuplicateException;

public interface ProductService {
    
    Page<Product> getProducts(int page, int size, String sortOption);

    public Optional<Product> getProductById(Long operationId);

    public int getProductStockById(Long operationId);

    public Product createProduct(ProductRequest productRequest) throws ProductDuplicateException;

    public void deleteProduct(Long productId);

    public Product updateProduct(Long productId, ProductRequest productRequest);



}
