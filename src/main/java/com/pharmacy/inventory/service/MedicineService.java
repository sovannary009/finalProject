package com.pharmacy.inventory.service;

import com.pharmacy.inventory.entity.Medicine;
import com.pharmacy.inventory.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository repository;

    public MedicineService(MedicineRepository repository) {
        this.repository = repository;
    }

    public Medicine save(Medicine medicine) {
        return repository.save(medicine);
    }

    public List<Medicine> getAll() {
        return repository.findAll();
    }

    public Medicine getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    public List<Medicine> search(String keyword) {
        return repository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(keyword, keyword);
    }
}


