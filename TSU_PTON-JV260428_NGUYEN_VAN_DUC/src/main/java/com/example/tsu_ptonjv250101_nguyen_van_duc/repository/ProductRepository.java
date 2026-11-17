package com.example.tsu_ptonjv250101_nguyen_van_duc.repository;

import com.example.tsu_ptonjv250101_nguyen_van_duc.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName(String productName);

    List<Product> findByProductNameContainingIgnoreCase(String productName);

    List<Product> findByStatus(Byte status);
}
