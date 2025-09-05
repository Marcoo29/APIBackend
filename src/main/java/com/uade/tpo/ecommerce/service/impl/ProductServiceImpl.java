package com.uade.tpo.ecommerce.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.exceptions.ProductDuplicateException;
import com.uade.tpo.ecommerce.repository.ProductRepository;
import com.uade.tpo.ecommerce.service.inter.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
     @Autowired
    private ProductRepository productRepository;

    public Page<Product> getProducts(PageRequest pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public Optional<Product> getProductAvailabilityById(Long productId) {
        return productRepository.findById(productId);
    }

    public Product createProduct(Product newProduct) throws ProductDuplicateException {
        Product product = productRepository.findByName(newProduct.getName());
        if (product == null)
            return productRepository.save(newProduct);
        throw new ProductDuplicateException();
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId); //cambiar
    }

    public void updateProduct(Long productId) {

    }

}
