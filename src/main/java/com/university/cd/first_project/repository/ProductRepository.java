package com.university.cd.first_project.repository;

import com.university.cd.first_project.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepository extends InMemoryRepository<Product> {

    public Optional<Product> findBySkuIgnoreCase(String sku) {
        return findAll().stream()
                .filter(p -> p.getSku().equalsIgnoreCase(sku))
                .findFirst();
    }

    public boolean existsBySkuIgnoreCase(String sku) {
        return findBySkuIgnoreCase(sku).isPresent();
    }
}
