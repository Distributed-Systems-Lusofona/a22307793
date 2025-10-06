package com.university.cd.first_project.service;

import com.university.cd.first_project.exception.ProductNotFoundException;
import com.university.cd.first_project.mapper.ProductMapper;
import com.university.cd.first_project.model.Product;
import com.university.cd.first_project.model.dto.ProductRequest;
import com.university.cd.first_project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;

    public Product createProduct(ProductRequest request) {
        if (repo.existsBySkuIgnoreCase(request.getSku())) {
            throw new IllegalArgumentException("SKU must be unique");
        }

        Product product = ProductMapper.toEntity(request);

        product.setId(UUID.randomUUID());
        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(Instant.now());

        return repo.save(product);
    }

    public Product getProductById(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product updateProduct(UUID id, ProductRequest request) {
        Product existing = getProductById(id);

        existing.setName(request.getName().trim());
        existing.setDescription(request.getDescription());

        if (!existing.getSku().equalsIgnoreCase(request.getSku())
                && repo.existsBySkuIgnoreCase(request.getSku())) {
            throw new IllegalArgumentException("SKU must be unique");
        }

        existing.setSku(request.getSku().trim());
        existing.setPrice(request.getPrice());
        existing.setStock(request.getStock());
        existing.setCurrency(request.getCurrency() != null ? request.getCurrency() : "EUR");

        existing.setUpdatedAt(Instant.now());

        return repo.save(existing);
    }

    public void deleteProduct(UUID id) {
        if (!repo.findById(id).isPresent()) {
            throw new ProductNotFoundException(id);
        }
        repo.deleteById(id);
    }
}
