package com.relations.all.service;

import com.relations.all.model.Product;
import com.relations.all.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;


    public Optional<Product> findCompaniesByProductId(Long productId, Pageable pageable) {
        return productRepo.findById(productId);
    }
}
