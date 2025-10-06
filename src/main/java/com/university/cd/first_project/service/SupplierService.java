package com.university.cd.first_project.service;

import com.university.cd.first_project.exception.ResourceNotFoundException;
import com.university.cd.first_project.model.Supplier;
import com.university.cd.first_project.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository repository;

    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }

    public List<Supplier> getAllSuppliers() {
        return repository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
    }

    public Supplier createSupplier(Supplier supplier) {
        return repository.save(supplier);
    }

    public Supplier updateSupplier(Long id, Supplier supplierDetails) {
        Supplier existing = getSupplierById(id);
        existing.setCompanyName(supplierDetails.getCompanyName());
        existing.setEmail(supplierDetails.getEmail());
        existing.setAddress(supplierDetails.getAddress());
        existing.setContactPerson(supplierDetails.getContactPerson());
        existing.setPhoneNumber(supplierDetails.getPhoneNumber());
        return repository.save(existing);
    }

    public void deleteSupplier(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Supplier not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
