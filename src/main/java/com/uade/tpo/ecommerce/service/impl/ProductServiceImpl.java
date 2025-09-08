package com.uade.tpo.ecommerce.service.impl;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.User;
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

    public int getProductStockById(Long productId) {
        return productRepository.findStockById(productId);
    }

    public Product createProduct(Product newProduct) throws ProductDuplicateException {
        Product product = productRepository.findByName(newProduct.getName());
        if (product == null)
            return productRepository.save(newProduct);
        throw new ProductDuplicateException();
    }

    public void deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductStatus(com.uade.tpo.ecommerce.entity.enums.ProductStatus.NOT_AVAILABLE);
            productRepository.save(product);
        }
    }

    public Product updateProduct(Product product){
        return productRepository.save(product);
    }

}
