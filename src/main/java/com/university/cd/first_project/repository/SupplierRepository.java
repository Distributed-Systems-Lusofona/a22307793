package com.university.cd.first_project.repository;

import com.university.cd.first_project.model.Supplier;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SupplierRepository {

    private final Map<Long, Supplier> suppliers = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Supplier> findAll() {
        return new ArrayList<>(suppliers.values());
    }

    public Optional<Supplier> findById(Long id) {
        return Optional.ofNullable(suppliers.get(id));
    }

    public Supplier save(Supplier supplier) {
        if (supplier.getId() == null) {
            supplier.setId(idCounter.getAndIncrement());
        }
        suppliers.put(supplier.getId(), supplier);
        return supplier;
    }

    public void deleteById(Long id) {
        suppliers.remove(id);
    }

    public boolean existsById(Long id) {
        return suppliers.containsKey(id);
    }
}
