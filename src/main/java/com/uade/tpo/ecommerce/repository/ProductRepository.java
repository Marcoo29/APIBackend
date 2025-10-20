package com.uade.tpo.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.enums.ProductStatus;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 🔹 Método nuevo para búsqueda + paginación + sort
    @Query("SELECT p FROM Product p " +
            "WHERE p.productStatus = :status " +
            "AND LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Product> searchProducts(
            @Param("status") ProductStatus status,
            @Param("name") String name,
            Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    Product findByName(String name);

    @Query("SELECT p.stock FROM Product p WHERE p.id = ?1")
    int findStockById(Long productId);
}
