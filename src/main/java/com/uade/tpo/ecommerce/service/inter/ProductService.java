package com.uade.tpo.ecommerce.service.inter;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.exceptions.ProductDuplicateException;

@Service
public interface ProductService {
    public Page<Product> getProducts(PageRequest pageRequest);

    public Optional<Product> getProductById(Long operationId);

    public int getProductStockById(Long operationId);

    public Product createProduct(Product newProduct) throws ProductDuplicateException;

    public void deleteProduct(Long productId);

    public Product updateProduct(Product product);



}
