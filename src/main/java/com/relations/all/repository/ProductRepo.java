package com.relations.all.repository;

import com.relations.all.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
    Optional<Product> findById(Long productId);
}
