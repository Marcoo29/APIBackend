package com.uade.tpo.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Category;
import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.ProductRequest;
import com.uade.tpo.ecommerce.entity.enums.ProductStatus;
import com.uade.tpo.ecommerce.exceptions.ProductDuplicateException;
import com.uade.tpo.ecommerce.repository.ProductRepository;
import com.uade.tpo.ecommerce.service.inter.CategoryService;
import com.uade.tpo.ecommerce.service.inter.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Page<Product> getProducts(int page, int size, String sortOption, String searchTerm) {
        Sort sort;

        switch (sortOption) {
            case "name-asc":
                sort = Sort.by("name").ascending();
                break;
            case "name-desc":
                sort = Sort.by("name").descending();
                break;
            case "price-asc":
                sort = Sort.by("price").ascending();
                break;
            case "price-desc":
                sort = Sort.by("price").descending();
                break;
            default:
                sort = Sort.by("id").ascending();
        }

        PageRequest pageable = PageRequest.of(page, size, sort);

        String query = (searchTerm == null) ? "" : searchTerm;
        return productRepository.searchProducts(ProductStatus.AVAILABLE, query, pageable);
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Page<Product> getProductsByCategory(Long categoryId, int page, int size, String sortOption,
            String searchTerm) {
        Sort sort;
        switch (sortOption) {
            case "name-asc":
                sort = Sort.by("name").ascending();
                break;
            case "name-desc":
                sort = Sort.by("name").descending();
                break;
            case "price-asc":
                sort = Sort.by("price").ascending();
                break;
            case "price-desc":
                sort = Sort.by("price").descending();
                break;
            default:
                sort = Sort.by("id").ascending();
        }

        PageRequest pageable = PageRequest.of(page, size, sort);

        if (searchTerm != null && !searchTerm.isEmpty()) {
            return productRepository.findAvailableByCategoryAndNameContaining(categoryId, searchTerm, pageable);
        } else {
            return productRepository.findAvailableByCategory(categoryId, pageable);
        }
    }

    @Override
    public int getProductStockById(Long productId) {
        return productRepository.findStockById(productId);
    }

    @Override
    public Product createProduct(ProductRequest productRequest) throws ProductDuplicateException {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setManufacturer(productRequest.getManufacturer());
        product.setStock(productRequest.getStock());
        product.setDescription(productRequest.getDescription());
        product.setFitFor(productRequest.getFitFor());
        product.setProductStatus(productRequest.getProductStatus());

        if (productRequest.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(productRequest.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            product.setCategory(category);
        }

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (productRequest.getName() != null)
            product.setName(productRequest.getName());
        if (productRequest.getPrice() != 0)
            product.setPrice(productRequest.getPrice());
        if (productRequest.getManufacturer() != null)
            product.setManufacturer(productRequest.getManufacturer());
        if (productRequest.getStock() != 0)
            product.setStock(productRequest.getStock());
        if (productRequest.getDescription() != null)
            product.setDescription(productRequest.getDescription());
        if (productRequest.getFitFor() != null)
            product.setFitFor(productRequest.getFitFor());
        if (productRequest.getProductStatus() != null)
            product.setProductStatus(productRequest.getProductStatus());

        if (productRequest.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(productRequest.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            product.setCategory(category);
        }

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductStatus(com.uade.tpo.ecommerce.entity.enums.ProductStatus.NOT_AVAILABLE);
            productRepository.save(product);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
